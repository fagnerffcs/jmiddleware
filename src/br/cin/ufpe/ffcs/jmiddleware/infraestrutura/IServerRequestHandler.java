package br.cin.ufpe.ffcs.jmiddleware.infraestrutura;

import java.io.IOException;

public interface IServerRequestHandler {
	public void receive() throws IOException;
	//public void send(byte[] msg) throws IOException;
}
