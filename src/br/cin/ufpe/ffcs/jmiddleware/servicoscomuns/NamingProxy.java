package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;

public class NamingProxy extends UnicastRemoteObject implements INaming {

	public NamingProxy() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = -2801070644280952168L;
	
	private HashMap<String, ClientProxy> repository = new HashMap<String, ClientProxy>();

	@Override
	public void register(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable {
		repository.put(serviceName, clientProxy);
	}

	@Override
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		return repository.get(serviceName);
	}

	@Override
	public List<String> list() throws UnknownHostException, IOException, Throwable {
		return Arrays.asList(repository.keySet().toArray().toString());
	}

}
