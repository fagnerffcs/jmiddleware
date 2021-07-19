package br.cin.ufpe.ffcs.jmiddleware.infraestrutura;

import java.io.IOException;

public interface IServerRequestHandler {
	public void sendReceive(byte[] msg) throws IOException;
}
