package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP {
	private ServerSocket serverSocket;
	
	public ServerRequestHandlerTCP(int porta) throws IOException{
		this.serverSocket = new ServerSocket(porta);
	}
	
	public void send(byte[] msgToClient) throws IOException {
		Socket conn = serverSocket.accept();
		DataOutputStream saida = null;
		
		saida = new DataOutputStream(conn.getOutputStream());
		saida.writeInt(msgToClient.length);
		saida.write(msgToClient);
		saida.flush();
		
		saida.close();
		conn.close();
	}
	
	public byte[] receive() throws IOException, ClassNotFoundException {
		Socket conn = serverSocket.accept();
		DataInputStream entrada = null;

		entrada = new DataInputStream(conn.getInputStream());
		int tamanho = entrada.readInt();
		byte[] msgRecebida = new byte[tamanho];
		entrada.read(msgRecebida, 0, tamanho);
		
		entrada.close();
		conn.close();
		
		return msgRecebida;
	}
}