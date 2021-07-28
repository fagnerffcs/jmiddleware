package br.cin.ufpe.ffcs.jmiddleware;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseInvoker;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.INaming;

public class Server {
	
	public static void main(String[] args) throws Throwable {
		//create a built-in proxy do naming service
		Registry registry = LocateRegistry.getRegistry(1313);
		if(registry.list().length == 0) {
			System.out.println("Servico de nome nao foi inicializado corretamente.");
		}
		INaming naming = (INaming) registry.lookup("NamingProxy");
		
		//create a instance of ConverCaseProxy
		ConvertCaseProxy convertCaseProxy = new ConvertCaseProxy("localhost", 1300, MiddlewareProtocol.TCP);
		
		//register service
		naming.register("ConvertCase", convertCaseProxy);
		
		//control loop to middleware
		System.out.println("Server is running...");
		ConvertCaseInvoker invoker = new ConvertCaseInvoker();
		invoker.invoke();
	}
}
