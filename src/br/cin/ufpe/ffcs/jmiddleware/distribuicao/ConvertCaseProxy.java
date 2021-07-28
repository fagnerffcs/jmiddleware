package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.util.ArrayList;

import br.cin.ufpe.ffcs.jmiddleware.negocio.IConvertCase;

public class ConvertCaseProxy extends ClientProxy implements IConvertCase {

	private static final long serialVersionUID = 8464535779877990964L;

	public ConvertCaseProxy() {
		super();
	}
	
	public ConvertCaseProxy(String host, int port, MiddlewareProtocol protocol) {
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
	public String convertToLower(String mensagem) {
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

}
