package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;

public class ClientRequestHandlerTCP implements IClientRequestHandler {
	private int porta;
	private String host;
	private Socket socket;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;

	public ClientRequestHandlerTCP(String host, int porta) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.porta = porta;
		this.socket = new Socket(this.host, this.porta);
	}

	public byte[] sendReceive(byte[] msg) throws UnknownHostException, IOException, ClassNotFoundException {
		//create streams
		this.entrada = new ObjectInputStream(socket.getInputStream());
		this.saida = new ObjectOutputStream(socket.getOutputStream());
		
		// send data to using TCP Channel
		this.saida.writeObject(msg);
		this.saida.flush();

		// receive data
		byte[] retorno = (byte[]) entrada.readObject();
		
		//close streams
		this.entrada.close();
		this.saida.close();

		return retorno;
	}
}