package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;

public class NamingImpl {
	private Map<String, ClientProxy> repository = new HashMap<String, ClientProxy>();

	public String register(Object parameter1, Object parameter2) {
		String name = (String) parameter1;
		ClientProxy clientProxy = (ClientProxy) parameter2;
		repository.put(name, clientProxy);
		System.out.println(clientProxy);
		return "true";
	}

	public ClientProxy lookup(Object objectName) {
		ClientProxy registeredProxy = repository.get(objectName);
		if(registeredProxy!=null) {
			return registeredProxy;	
		} else {
			return null;
		}
	}

	public String list() {
		return  Arrays.asList(repository.values()).toString();
	}

}
