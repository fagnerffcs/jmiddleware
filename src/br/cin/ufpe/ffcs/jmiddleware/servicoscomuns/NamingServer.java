package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.util.logging.Logger;

public class NamingServer {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String[] args) throws Throwable {
		LOGGER.info("Servidor de nomes iniciado com sucesso.");
		
		NamingInvoker invoker = new NamingInvoker();
		//control loop to middleware
		invoker.invoke();
	}
}
