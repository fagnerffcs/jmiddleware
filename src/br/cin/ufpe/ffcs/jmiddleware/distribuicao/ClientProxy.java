package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;
import java.util.ArrayList;

import br.cin.ufpe.ffcs.jmiddleware.model.IConvertCase;
import br.cin.ufpe.ffcs.jmiddleware.model.MiddlewareProtocol;

public class ClientProxy implements Serializable, IConvertCase {
	
	private static final long serialVersionUID = 1L;
	private String host;
	private int port;
	private MiddlewareProtocol protocol;
	
	public ClientProxy() {
		super();
	}
	
	public ClientProxy(int port, String host, MiddlewareProtocol protocol) {
		super();
		this.host = host;
		this.port = port;
		this.protocol = protocol;
	}

	@Override
	public String convertToUpper(String mensagem) {
		try {
			Requestor requestor = new Requestor(protocol, host, port);
			ArrayList<String> params = new ArrayList<String>();
			params.add(mensagem);
			Request request = new Request("convertToUpper", params);
			Invocation invocation = new Invocation(request);
			return requestor.invoke(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public <T> String convertToLower(String mensagem) {
		try {
			Requestor requestor = new Requestor(protocol, host, port);
			ArrayList<String> params = new ArrayList<String>();
			params.add(mensagem);
			Request request = new Request("convertToLower", params);
			Invocation invocation = new Invocation(request);
			return requestor.invoke(invocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public MiddlewareProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(MiddlewareProtocol protocol) {
		this.protocol = protocol;
	}
	
}
