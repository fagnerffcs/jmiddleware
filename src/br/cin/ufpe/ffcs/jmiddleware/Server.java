package br.cin.ufpe.ffcs.jmiddleware;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseInvoker;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.INaming;

public class Server {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String[] args) throws Throwable {
		//create a built-in proxy do naming service
		Registry registry = LocateRegistry.getRegistry(1313);
		if(registry.list().length == 0) {
			LOGGER.log(Level.SEVERE, "Servico de nome nao foi inicializado corretamente.");
		}
		INaming naming = (INaming) registry.lookup("NamingProxy");
		
		//create a instance of ConverCaseProxy
		ConvertCaseProxy convertCaseProxy = new ConvertCaseProxy("localhost", 1300, MiddlewareProtocol.TCP);
		
		//register service
		naming.register("ConvertCase", convertCaseProxy);
		
		//control loop to middleware
		LOGGER.info("Servidor iniciado com sucesso.");
		ConvertCaseInvoker invoker = new ConvertCaseInvoker();
		invoker.invoke();
	}
}
