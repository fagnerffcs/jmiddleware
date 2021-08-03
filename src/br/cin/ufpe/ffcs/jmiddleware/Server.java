package br.cin.ufpe.ffcs.jmiddleware;

import java.util.logging.Logger;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseInvoker;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.NamingProxy;

public class Server {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String[] args) throws Throwable {
		//create a built-in proxy do naming service
		NamingProxy namingProxy = new NamingProxy("localhost", 1313, MiddlewareProtocol.TCP);
		
		//create a instance of ConverCaseProxy
		ConvertCaseProxy convertCaseProxy = new ConvertCaseProxy("localhost", 1300, MiddlewareProtocol.TCP);
		
		//register service
		namingProxy.register("ConvertCase", convertCaseProxy);
		
		//control loop to middleware
		LOGGER.info("Servidor iniciado com sucesso.");
		ConvertCaseInvoker invoker = new ConvertCaseInvoker();
		invoker.invoke();
	}
}
