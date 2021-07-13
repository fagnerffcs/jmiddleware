package br.cin.ufpe.ffcs.jmiddleware;

import java.io.IOException;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ClientProxy;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, 
												  InterruptedException {
		ClientProxy clientProxy = new ClientProxy(1300, "localhost", "RemoteObject");
		clientProxy.enviarMensagem("frase com letras minusculas");
		String retorno = clientProxy.receberMensagem();
		System.out.println("Mensagem do servidor: " + retorno);
	}
}