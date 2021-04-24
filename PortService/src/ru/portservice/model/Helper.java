package ru.portservice.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
	
	public static long getDurationInMinutes(Date earlier, Date later) {
		long durationInMillisec = later.getTime() - earlier.getTime();			
		long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillisec);
		return durationInMinutes;
	}
	
	public static String minutesToString(long minutesCount) {
		int minutes = (int)(minutesCount % 60);
		minutesCount /= 60;
		int hours = (int)(minutesCount % 24);
		minutesCount /= 24;
		int days = (int)minutesCount;            
        return days + ":" + hours + ":" + minutes;
	}
	
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
}
