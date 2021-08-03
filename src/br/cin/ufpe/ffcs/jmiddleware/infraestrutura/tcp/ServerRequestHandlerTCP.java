package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP {
	private ServerSocket serverSocket;
	private Socket conn;

	private DataOutputStream saida = null;
	private DataInputStream entrada = null;
	
	public ServerRequestHandlerTCP(int porta) throws IOException{
		this.serverSocket = new ServerSocket(porta);
	}
	
	public void send(byte[] msgToClient) throws IOException {
		conn = serverSocket.accept();

		saida = new DataOutputStream(conn.getOutputStream());
		saida.writeInt(msgToClient.length);
		saida.write(msgToClient);
		saida.flush();
		
		saida.close();
		conn.close();
	}
	
	public byte[] receive() throws IOException, ClassNotFoundException {
		conn = serverSocket.accept();

		byte[] msgRecebida = null;
		entrada = new DataInputStream(conn.getInputStream());
		int tamanho = entrada.readInt();
		entrada.read(msgRecebida, 0, tamanho);
		
		entrada.close();
		conn.close();
		
		return msgRecebida;
	}
}