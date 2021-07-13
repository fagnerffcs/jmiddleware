package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;
import java.io.Serializable;

import br.cin.ufpe.ffcs.jmiddleware.model.IChat;
import br.cin.ufpe.ffcs.jmiddleware.model.MiddlewareProtocol;

public class ClientProxy implements Serializable, IChat {
	
	private static final long serialVersionUID = 1L;
	private int port;
	private String host;
	private String objectId;
	private Requestor requestor;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public ClientProxy() {
		super();
	}
	
	public ClientProxy(int port, String host, String objectId) {
		super();
		this.port = port;
		this.host = host;
		this.objectId = objectId;
		this.requestor = new Requestor(MiddlewareProtocol.TCP, "localhost", this.getPort());
	}

	@Override
	public void enviarMensagem(String mensagem) {
		try {
			requestor.send(mensagem);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String receberMensagem() {
		try {
			return requestor.receive();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
}
