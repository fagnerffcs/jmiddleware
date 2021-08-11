package br.cin.ufpe.ffcs.jmiddleware;

import java.io.IOException;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.NamingProxy;

public class Client {
	
	public void iniciarCliente(String frase) throws UnknownHostException, IOException, Throwable {
		NamingProxy namingProxy = new NamingProxy("localhost", 1313, MiddlewareProtocol.TCP);
		ConvertCaseProxy clientProxy = (ConvertCaseProxy) namingProxy.lookup("ConvertCase");
		clientProxy.convertToUpper(frase);
	}
}