package ru.portservice.client;

import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.portservice.model.Helper;
import ru.portservice.model.Report;
import ru.portservice.model.Schedule;
import ru.portservice.service2.IWebService2;

public class PortWebServiceClient {

	public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8001/wss/service2?wsdl");

        QName qname = new QName("http://service2.portservice.ru/", "WebService2Service");

        Service service2 = Service.create(url, qname);
        IWebService2 webService2 = service2.getPort(IWebService2.class);

        try {
        	String resultJson = webService2.getResult(100);        	
        	StringReader reader = new StringReader(resultJson);
    		ObjectMapper mapper = new ObjectMapper();
    		Report report = mapper.readValue(reader, Report.class);
    		
    		JavaType javaType = Helper.getCollectionType(ArrayList.class, Schedule.class);
    		reader = new StringReader(report.unloadings);
    		mapper = new ObjectMapper();
    		ArrayList<Schedule> unloadings = mapper.readValue(reader, javaType);
    		
    		int totalWaiting = 0, totalUnloading = 0, maxDelay = unloadings.get(0).getUnloadingDelay();
    		
    		for(Schedule unloading : unloadings) {
    			System.out.println(unloading);
    			totalWaiting += Helper.getDurationInMinutes(unloading.getRealArrivalTime(), 
    					unloading.getStartUnloadingTime());
    			totalUnloading += unloading.getUnloadingDelay();
    			if(unloading.getUnloadingDelay() > maxDelay)
    				maxDelay = unloading.getUnloadingDelay();
    		}
    		
    		System.out.println("Всего разгрузок: " + unloadings.size());
    		System.out.println("Количество кранов для разгрузки сыпучего груза: " + 
    				report.looseCranesCount);
    		System.out.println("Количество кранов для разгрузки жидкого груза: " + 
    				report.liqudCranesCount);
    		System.out.println("Количество кранов для разгрузки контейнеров: " + 
    				report.containerCranesCount);
    		System.out.println("Всего начислено штрафа: " + report.totalFine);
    		System.out.println("Среднее время ожидание в очереди: " + 
    				Helper.minutesToString(totalWaiting / unloadings.size()));
    		System.out.println("Среднее время задержки разгрузки: " + 
    				Helper.minutesToString(totalUnloading / unloadings.size()));
    		System.out.println("Максимальное время задержки разгрузки: " + 
    				Helper.minutesToString(maxDelay));
        }
        catch(IOException ex) {
        	System.out.println(ex.getMessage());
        }
	}
}
