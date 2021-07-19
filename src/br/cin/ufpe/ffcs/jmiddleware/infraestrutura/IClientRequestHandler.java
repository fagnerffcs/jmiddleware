package br.cin.ufpe.ffcs.jmiddleware.infraestrutura;

import java.io.IOException;

public interface IClientRequestHandler {
	public byte[] receive() throws IOException, InterruptedException, ClassNotFoundException;
	public void send(byte[] msg) throws IOException, InterruptedException;
}
