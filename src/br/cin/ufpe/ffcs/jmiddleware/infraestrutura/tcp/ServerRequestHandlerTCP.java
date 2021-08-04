package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP {
	private ServerSocket serverSocket;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private Socket conn;
	
	public ServerRequestHandlerTCP(int porta) throws IOException{
		this.serverSocket = new ServerSocket(porta);
		this.conn = serverSocket.accept();
		saida = new ObjectOutputStream(conn.getOutputStream());
		entrada = new ObjectInputStream(conn.getInputStream());
	}
	
	public void send(byte[] msgToClient) throws IOException {
		saida.writeObject(msgToClient);
		saida.flush();
	}
	
	public byte[] receive() throws IOException, ClassNotFoundException {
		byte[] msgRecebida = (byte[]) entrada.readObject();
		return msgRecebida;
	}
}