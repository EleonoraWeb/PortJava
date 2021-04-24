package ru.portservice.server;


import javax.xml.ws.Endpoint;

import ru.portservice.service1.WebService1;
import ru.portservice.service2.WebService2;
import ru.portservice.service3.WebService3;

public class WebServer {

	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:8001/wss/service1", new WebService1());
		Endpoint.publish("http://localhost:8001/wss/service2", new WebService2());
		Endpoint.publish("http://localhost:8001/wss/service3", new WebService3());

	}

}
