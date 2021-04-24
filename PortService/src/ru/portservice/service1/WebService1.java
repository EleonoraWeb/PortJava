package ru.portservice.service1;

import java.io.IOException;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.portservice.service1.IWebService1")
public class WebService1 implements IWebService1 {
	@Override
	public String getSchedule(int shipsCount) throws IOException {
		return Generator.Generate(shipsCount);
	}
}
