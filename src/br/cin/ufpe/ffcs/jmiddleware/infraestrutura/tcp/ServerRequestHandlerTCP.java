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
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	public ServerRequestHandlerTCP(int porta){
		this.porta = porta;
	}
	
	public void send(byte[] msgToClient) throws IOException {
		serverSocket = new ServerSocket(porta);
		conn = serverSocket.accept();

		outToClient = new DataOutputStream(conn.getOutputStream());
		inFromClient = new DataInputStream(conn.getInputStream());
		
		tamanhoMensagemEnviada = msgToClient.length;
		outToClient.writeInt(tamanhoMensagemEnviada);
		outToClient.write(msgToClient);
		outToClient.flush();		
		
//		serverSocket.close();
//		conn.close();
//		outToClient.close();
//		inFromClient.close();
	}
	
	public byte[] receive() throws IOException {
		byte[] msgRecebida = null;
		serverSocket = new ServerSocket(porta);
		conn = serverSocket.accept();

		outToClient = new DataOutputStream(conn.getOutputStream());
		inFromClient = new DataInputStream(conn.getInputStream());
		
		tamanhoMensagemRecebida = inFromClient.readInt();
		msgRecebida = new byte[tamanhoMensagemRecebida];
		
		inFromClient.read(msgRecebida, 0, tamanhoMensagemRecebida);
		
//		serverSocket.close();
//		conn.close();
//		outToClient.close();
//		inFromClient.close();
		
		return msgRecebida;
	}
	
}

