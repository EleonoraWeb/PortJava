package ru.portservice.service2;

import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IWebService2 {

	@WebMethod
    public String getResult(int shipsCount)  throws IOException;
	
}
