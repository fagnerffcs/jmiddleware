package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;

public class ClientRequestHandlerTCP implements IClientRequestHandler {
	
	private String host;
	private int porta;
	
	private Socket clientSocket = null;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
	
	public ClientRequestHandlerTCP(String host, int porta) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.porta = porta;
		this.clientSocket = new Socket(this.host, this.porta);
	}
	
	public void send(byte[] msg) throws IOException, InterruptedException{
		saida = new ObjectOutputStream(clientSocket.getOutputStream());
		saida.writeInt(msg.length);
		saida.write(msg);
		saida.flush();
	}
	
	public byte[] receive() throws IOException, InterruptedException{
		String retorno = "";
		entrada = new ObjectInputStream(clientSocket.getInputStream());
		try {
			retorno = (String) entrada.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno.getBytes();
	}

}