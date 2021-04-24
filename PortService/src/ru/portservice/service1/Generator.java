package ru.portservice.service1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.portservice.model.CargoType;
import ru.portservice.model.Schedule;

public class Generator {
	//Генератор случайных чисел
	private static Random rnd = new Random();
	
	//Список кораблей
	private final static ArrayList<Schedule> schedule = new ArrayList<Schedule>();;
		
	public static String Generate(int count)  throws IOException {
				
		Calendar c;
		
		for(int i = 0; i < count; i++) {
			Schedule record = new Schedule();
			
			int type = rnd.nextInt(3);			
			switch(type) {
			case 0:
				record.setCargoType(CargoType.сыпучий);
				break;
			case 1:
				record.setCargoType(CargoType.жидкий);
				break;
			case 2:
				record.setCargoType(CargoType.контейнер);
				break;
			}
			
			record.setShipName("Корабль №" + (i + 1));
			
			c = new GregorianCalendar(2021, Calendar.APRIL, 
					1 + rnd.nextInt(30), rnd.nextInt(24), rnd.nextInt(60), 00);
			Date plannedArrivalTime = c.getTime();
			c.add(Calendar.DATE, -7 + rnd.nextInt(14));
			Date realArrivalTime = c.getTime();
			
			record.setPlannedArrivalTime(plannedArrivalTime);
			record.setRealArrivalTime(realArrivalTime);
			
			record.setCargoValue(1000 * (1 + rnd.nextInt(20)));
			
			record.setUnloadingDelay(rnd.nextInt(1440));
			
			schedule.add(record);
		}
		
		//сортируем расписание по реальному времени прибытия кораблей в порт
		Collections.sort(schedule, new Comparator<Schedule>() {
		    @Override
		    public int compare(Schedule left, Schedule right) {
		        return left.getRealArrivalTime().after(right.getRealArrivalTime()) ? 1 : 
		        	(left.getRealArrivalTime().before(right.getRealArrivalTime())) ? -1 : 0;
		    }
		});
		
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(writer, schedule);
		return writer.toString();
	}
}
