package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Invoker;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IServerRequestHandler;

public class ServerRequestHandlerTCP implements IServerRequestHandler {
	private int porta;

	public ServerRequestHandlerTCP(int porta){
		this.porta = porta;
	}
	
	public void receive() throws IOException {
		Invoker inv = new Invoker(this.porta);
		inv.invoke();
	}
	
}
