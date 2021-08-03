package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.util.List;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;

public interface INaming extends Remote {
	public boolean register(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable;
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable;
	public List<String> list() throws UnknownHostException, IOException, Throwable;
}
