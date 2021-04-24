package ru.portservice.service3;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IWebService3 {

	@WebMethod
    public String getSimulation(String schedule)  throws IOException;
	
}
