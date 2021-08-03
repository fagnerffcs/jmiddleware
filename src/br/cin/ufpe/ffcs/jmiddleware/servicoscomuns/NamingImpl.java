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
		return "sucesso";
	}

	public ClientProxy lookup(Object objectName) {
		return repository.get(objectName);
	}

	public String list(Object string) {
		return  Arrays.asList(repository.values()).toString();
	}

}
