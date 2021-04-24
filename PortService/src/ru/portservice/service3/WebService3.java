package ru.portservice.service3;

import java.io.IOException;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.portservice.service3.IWebService3")
public class WebService3 implements IWebService3 {
	@Override
	public String getSimulation(String schedule) throws IOException {
		return PortSimulator.Processing(schedule);
	}
}
