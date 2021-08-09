package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP implements Runnable {
	private ServerSocket serverSocket;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private Socket conn;

	public ServerRequestHandlerTCP(int porta) throws IOException {
		this.serverSocket = new ServerSocket(porta);
	}
	
	@Override
	public void run() {
		try {
			this.conn = this.serverSocket.accept();
			saida = new ObjectOutputStream(conn.getOutputStream());
			entrada = new ObjectInputStream(conn.getInputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void send(byte[] msgToClient) throws IOException {
		saida.writeObject(msgToClient);
		saida.flush();
	}

	public synchronized byte[] receive() throws IOException, ClassNotFoundException {
		byte[] msgRecebida = (byte[]) entrada.readObject();
		return msgRecebida;
	}
}