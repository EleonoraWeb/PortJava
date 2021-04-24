package ru.portservice.service2;

import java.io.IOException;
import java.net.URL;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ru.portservice.service1.IWebService1;
import ru.portservice.service3.IWebService3;

@WebService(endpointInterface = "ru.portservice.service2.IWebService2")
public class WebService2 implements IWebService2 {
	@Override
	public String getResult(int shipsCount) throws IOException {
		
		URL service1Url = new URL("http://localhost:8001/wss/service1?wsdl");
		URL service3Url = new URL("http://localhost:8001/wss/service3?wsdl");
		QName service1QName = new QName("http://service1.portservice.ru/", "WebService1Service");
		QName service3QName = new QName("http://service3.portservice.ru/", "WebService3Service");
		Service service1 = Service.create(service1Url, service1QName);
		Service service3 = Service.create(service3Url, service3QName);
		IWebService1 webService1 = service1.getPort(IWebService1.class);
		IWebService3 webService3 = service3.getPort(IWebService3.class);
		
		String json = webService1.getSchedule(shipsCount);
		
		return webService3.getSimulation(json);
	}
}
