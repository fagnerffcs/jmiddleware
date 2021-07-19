package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;

public class ClientRequestHandlerTCP implements IClientRequestHandler {
	
	private String host;
	private int porta;
	
	private Socket clientSocket = null;
    private DataOutputStream saida;
    private DataInputStream entrada;
	
	public ClientRequestHandlerTCP(String host, int porta) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.porta = porta;
		this.clientSocket = new Socket(this.host, this.porta);
	}
	
	public void send(byte[] msg) throws IOException, InterruptedException{
		saida = new DataOutputStream(clientSocket.getOutputStream());
		saida.writeInt(msg.length);
		saida.write(msg);
		saida.flush();
	}
	
	public byte[] receive() throws IOException, InterruptedException{
		byte[] retorno = null;
		int tamanho;
		entrada = new DataInputStream(clientSocket.getInputStream());
		try {
			tamanho = entrada.readInt();
			retorno = new byte[tamanho];
			entrada.read(retorno, 0, tamanho);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

}