package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NamingServer {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String[] args) {
		try {
			NamingProxy namingProxy = new NamingProxy();
			Registry registry = LocateRegistry.createRegistry(1313);
			registry.rebind("NamingProxy", namingProxy);
			LOGGER.info("Servico de Nomes inicializado com sucesso.");
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Erro ao fazer o bind");
			e.printStackTrace();
		}
	}
}
