package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Invocation;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Request;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Requestor;

public class NamingProxy extends ClientProxy implements INaming {

	private static final long serialVersionUID = -6405977059068395845L;

	public NamingProxy(String host, int port, MiddlewareProtocol protocol) {
		super();
		this.host = host;
		this.port = port;
		this.protocol = protocol;
	}
	
	@Override
	public boolean register(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable {
		try {
			Requestor requestor = new Requestor(protocol, host, port);
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(serviceName);
			params.add(clientProxy);
			Request request = new Request("register", params);
			Invocation invocation = new Invocation(request);
			return requestor.invoke(invocation).equals("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		try {
			Requestor requestor = new Requestor(protocol, host, port);
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(serviceName);
			Request request = new Request("lookup", params);
			Invocation invocation = new Invocation(request);
			return (ClientProxy) requestor.invoke(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> list() throws UnknownHostException, IOException, Throwable {
		try {
			Requestor requestor = new Requestor(protocol, host, port);
			ArrayList<Object> params = new ArrayList<Object>();
			Request request = new Request("list", params);
			Invocation invocation = new Invocation(request);
			return (List<String>) requestor.invoke(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}