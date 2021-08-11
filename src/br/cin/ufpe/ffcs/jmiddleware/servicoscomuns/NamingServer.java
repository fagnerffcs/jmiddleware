package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.util.logging.Logger;

public class NamingServer {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final LifeCycleMode LI_MODE = LifeCycleMode.ENABLED;
	
	public static void main(String[] args) throws Throwable {
		LOGGER.info("Servidor de nomes iniciado com sucesso.");
		
		AbstractNamingInvoker invoker;
		if(LI_MODE.equals(LifeCycleMode.ENABLED)) {
			invoker = new NamingInvokerLCM();
		} else {
			invoker = new NamingInvoker();
		}

		//control loop to middleware
		invoker.invoke();
	}
}
