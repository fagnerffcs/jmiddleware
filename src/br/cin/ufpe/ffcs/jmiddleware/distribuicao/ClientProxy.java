package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;

public abstract class ClientProxy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String host;
	protected int port;
	protected MiddlewareProtocol protocol;

}
