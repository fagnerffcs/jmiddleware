package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IServerRequestHandler;

public class ServerRequestHandlerUDP implements IServerRequestHandler {
	private int porta;
	private DatagramSocket serverSocket = null;
	private DatagramPacket receivePacket = null;
	
	public ServerRequestHandlerUDP(int porta){
		this.porta = porta;
	}
	
	public void receive() throws IOException {
		byte[] receiveData = new byte[1024];
		serverSocket = new DatagramSocket(porta);
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		serverSocket.receive(receivePacket);		
	}
	
	public void send(byte[] msg) throws IOException {
		byte[] sendData = new byte[1024];
		InetAddress IPAddress = receivePacket.getAddress();
		int port = receivePacket.getPort();
		sendData = new String(msg).toUpperCase().getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		serverSocket.send(sendPacket);
	}
}