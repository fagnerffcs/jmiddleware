package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandlerTCP {
	private int porta;
	private ServerSocket serverSocket = null;
	private Socket conn = null;

	private int tamanhoMensagemEnviada;
	private int tamanhoMensagemRecebida;
	private DataOutputStream saida = null;
	private DataInputStream entrada = null;
	
	public ServerRequestHandlerTCP(int porta) throws IOException{
		this.porta = porta;
	}
	
	public void send(byte[] msgToClient) throws IOException {
		if(serverSocket==null || serverSocket.isClosed()) {
			serverSocket = new ServerSocket(porta);
		}
		conn = serverSocket.accept();

		saida = new DataOutputStream(conn.getOutputStream());
		entrada = new DataInputStream(conn.getInputStream());
		
		tamanhoMensagemEnviada = msgToClient.length;
		saida.writeInt(tamanhoMensagemEnviada);
		saida.write(msgToClient);
		saida.flush();
		
		serverSocket.close();
		conn.close();
		saida.close();
		entrada.close();
	}
	
	public byte[] receive() throws IOException {
		byte[] msgRecebida = null;
		if(serverSocket==null || serverSocket.isClosed()) {
			serverSocket = new ServerSocket(porta);
		}
		conn = serverSocket.accept();

		saida = new DataOutputStream(conn.getOutputStream());
		entrada = new DataInputStream(conn.getInputStream());
		
		tamanhoMensagemRecebida = entrada.readInt();
		msgRecebida = new byte[tamanhoMensagemRecebida];
		
		entrada.read(msgRecebida, 0, tamanhoMensagemRecebida);
		
		serverSocket.close();
		conn.close();
		saida.close();
		entrada.close();
		
		return msgRecebida;
	}
	
}

