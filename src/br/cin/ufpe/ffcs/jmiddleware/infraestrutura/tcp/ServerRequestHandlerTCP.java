package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Invoker;

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
	
	public void sendReceive() throws IOException {
		byte[] msgRecebida = null;
		serverSocket = new ServerSocket(porta);
		conn = serverSocket.accept();

		outToClient = new DataOutputStream(conn.getOutputStream());
		inFromClient = new DataInputStream(conn.getInputStream());
		
		tamanhoMensagemRecebida = inFromClient.readInt();
		msgRecebida = new byte[tamanhoMensagemRecebida];
		
		inFromClient.read(msgRecebida, 0, tamanhoMensagemRecebida);
		
		Invoker invoker = new Invoker();
		byte[] msgToClient = invoker.invoke(msgRecebida);

		tamanhoMensagemEnviada = msgToClient.length;
		outToClient.writeInt(tamanhoMensagemEnviada);
		outToClient.write(msgToClient);
		outToClient.flush();		
		
		serverSocket.close();
		conn.close();
		outToClient.close();
		inFromClient.close();

	}
	
}

