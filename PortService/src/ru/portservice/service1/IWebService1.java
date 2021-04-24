package ru.portservice.service1;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IWebService1 {

	@WebMethod
    public String getSchedule(int shipsCount)  throws IOException;
	
}
