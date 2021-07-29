package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;

public class ClientRequestHandlerTCP implements IClientRequestHandler {
	
	private String host;
	private int porta;
	
	private Socket clientSocket = null;
    private DataOutputStream saida;
    private DataInputStream entrada;
    
    private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public ClientRequestHandlerTCP(String host, int porta) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.porta = porta;
	}
	
	public void send(byte[] msg) throws IOException, InterruptedException{
		if(this.clientSocket==null || this.clientSocket.isClosed()) {
			this.clientSocket = new Socket(this.host, this.porta);
		}
		this.saida = new DataOutputStream(clientSocket.getOutputStream());
		this.saida.writeInt(msg.length);
		this.saida.write(msg);
		this.saida.flush();
	}
	
	public byte[] receive() throws IOException, InterruptedException{
		if(this.clientSocket==null || this.clientSocket.isClosed()) {
			this.clientSocket = new Socket(this.host, this.porta);
		}
		byte[] retorno = null;
		int tamanho;
		this.entrada = new DataInputStream(clientSocket.getInputStream());
		try {
			tamanho = entrada.readInt();
			retorno = new byte[tamanho];
			this.entrada.read(retorno, 0, tamanho);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return retorno;
	}

}