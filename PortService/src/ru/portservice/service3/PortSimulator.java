package ru.portservice.service3;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.portservice.model.CargoType;
import ru.portservice.model.Crane;
import ru.portservice.model.Helper;
import ru.portservice.model.Port;
import ru.portservice.model.Report;
import ru.portservice.model.Schedule;

public class PortSimulator {
	
	private final static Port port = new Port();
	
	public static String Processing(String scheduleJson) throws IOException{
		StringReader reader = new StringReader(scheduleJson);
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = Helper.getCollectionType(ArrayList.class, Schedule.class);
		ArrayList<Schedule> schedule = mapper.readValue(reader, javaType);
		ArrayList<Schedule> unloadings;
		
		while(true) {
			unloadings = UnloadingSimulation(schedule);
			if(Optimize(unloadings))
				break;
		}
		
		Report report = new Report();
		
		StringWriter writer = new StringWriter();
		mapper = new ObjectMapper();
		mapper.writeValue(writer, unloadings);
		
		report.unloadings = writer.toString();
		report.looseCranesCount = port.cranesOfType(CargoType.сыпучий);
		report.liqudCranesCount = port.cranesOfType(CargoType.жидкий);
		report.containerCranesCount = port.cranesOfType(CargoType.контейнер);
		
		int totalFine = 0;
		
		for(Schedule unloading : unloadings) {
			long realWaitingTime = Helper.getDurationInMinutes(unloading.getRealArrivalTime(), 
					unloading.getEndUnloadingTime());
			int fine = (int) ((realWaitingTime - unloading.getPlannedUnloadingTimeDuration()) / 60 * 100);			
			totalFine += fine;
		}
		report.totalFine = totalFine;
		
		writer = new StringWriter();
		mapper = new ObjectMapper();
		mapper.writeValue(writer, report);
		return writer.toString();
	}
	
	private static ArrayList<Schedule> UnloadingSimulation(ArrayList<Schedule> schedule) {
				
		for(int i=0; i < schedule.size(); i++) {
			
			Calendar c = new GregorianCalendar();
			
			ArrayList<Crane> cranes = port.getCranes(schedule.get(i).getCargoType());
			
			schedule.get(i).setPlannedUnloadingTimeDuration(schedule.get(i).getCargoValue() /
					port.getCraneProductivity(schedule.get(i).getCargoType()));
			
			int unloadingDuration = schedule.get(i).getCargoValue() /
					cranes.size() / port.getCraneProductivity(schedule.get(i).getCargoType()) +
							schedule.get(i).getUnloadingDelay();
			
			if(cranes.get(0).getLastWorkFinished() == null)
				c.setTime(schedule.get(i).getRealArrivalTime());
			else {
				Date start = 
						schedule.get(i).getRealArrivalTime().after(cranes.get(0).getLastWorkFinished()) ?
								schedule.get(i).getRealArrivalTime() :
									cranes.get(0).getLastWorkFinished();
				
				c.setTime(start);
			}
			
			schedule.get(i).setStartUnloadingTime(c.getTime());
						
			c.add(Calendar.MINUTE, unloadingDuration);
						
			schedule.get(i).setEndUnloadingTime(c.getTime());
			for(Crane crane : cranes)
				crane.setLastWorkFinished(c.getTime());
		}
		
		return schedule;
	}
	
	private static boolean Optimize(ArrayList<Schedule> schedule) {
		int totalFine = 0, looseFine = 0, liquidFine = 0, containerFine = 0;
		
		for(Schedule record : schedule) {
			long realWaitingTime = Helper.getDurationInMinutes(record.getRealArrivalTime(), record.getEndUnloadingTime());
			int fine = (int) ((realWaitingTime - record.getPlannedUnloadingTimeDuration()) / 60 * 100);
			
			totalFine += fine;
			
			switch(record.getCargoType()) {
			case сыпучий:
				looseFine += fine;
				break;
			case жидкий:
				liquidFine += fine;
				break;
			case контейнер:
				containerFine += fine;
				break;
			}
		}
		
		if(totalFine <= 50000)
			return true;
		
		port.reset();
		
		if(looseFine >= liquidFine && looseFine >= containerFine)
			port.addCrane(CargoType.сыпучий);
		else if(liquidFine >= looseFine && liquidFine >= containerFine)
			port.addCrane(CargoType.жидкий);
		else
			port.addCrane(CargoType.контейнер);
		
		return false;
	}
}
