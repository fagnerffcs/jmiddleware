package br.cin.ufpe.ffcs.jmiddleware;

import java.io.IOException;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;
import br.cin.ufpe.ffcs.jmiddleware.identification.AbsoluteObjectReference;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, 
												  InterruptedException {
		AbsoluteObjectReference aor = new AbsoluteObjectReference(1300, "localhost", "RemoteObject");
		ClientProxy clientProxy = new ClientProxy(aor.getPorta(), aor.getHost());
		System.out.println(clientProxy.convertToUpper("frase com letras minusculas"));
	}
}