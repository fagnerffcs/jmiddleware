package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandlerUDP {
	
	private String host;
	private int porta;
	private DatagramSocket clienteSocket = null;
	private DatagramPacket clientePacket = null;
	
	public ClientRequestHandlerUDP(String host, int porta) {
		super();
		this.host = host;
		this.porta = porta;
	}
	
	public void send(byte[] msg) throws IOException, InterruptedException{
		InetAddress IPAddress = InetAddress.getByName(host);
		clienteSocket = new DatagramSocket();
		byte[] dadosAEnviar = msg;
		clientePacket = new DatagramPacket(dadosAEnviar, dadosAEnviar.length, IPAddress, porta);		
		clienteSocket.send(clientePacket);
	}
	
	public byte[] receive() throws IOException, InterruptedException{
		byte[] dadosDoServidor = new byte[1024];
		String msgDoServidor = null;
		
		DatagramPacket pacotesDoServidor = new DatagramPacket(dadosDoServidor, dadosDoServidor.length);
		clienteSocket.receive(pacotesDoServidor);
		msgDoServidor = new String(pacotesDoServidor.getData()).toUpperCase();
		
		return msgDoServidor.getBytes();
	}

}