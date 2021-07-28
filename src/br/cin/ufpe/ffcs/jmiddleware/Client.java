package br.cin.ufpe.ffcs.jmiddleware;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.INaming;

public class Client {
	
	public static void main(String[] args) throws Throwable {
		Registry registry = LocateRegistry.getRegistry(1313);
		if(registry.list().length == 0) {
			System.out.println("Servico de nome nao foi inicializado corretamente.");
		}		
		INaming naming = (INaming) registry.lookup("NamingProxy");
		ConvertCaseProxy clientProxy = (ConvertCaseProxy) naming.lookup("ConvertCase");
		System.out.println(clientProxy.convertToUpper("frase com caixa alta"));
		System.out.println(clientProxy.convertToLower("FRASE COM CAIXA BAIXA"));
	}
}