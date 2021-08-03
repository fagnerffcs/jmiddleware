package br.cin.ufpe.ffcs.jmiddleware.infraestrutura;

import java.io.IOException;

public interface IClientRequestHandler {
	public byte[] sendReceive(byte[] msg) throws IOException, InterruptedException, ClassNotFoundException;
}
