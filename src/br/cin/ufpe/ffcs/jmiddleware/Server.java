package br.cin.ufpe.ffcs.jmiddleware;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp.ServerRequestHandlerTCP;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.udp.ServerRequestHandlerUDP;
import br.cin.ufpe.ffcs.jmiddleware.model.MiddlewareProtocol;

public class Server {
	
	private static MiddlewareProtocol protocol = MiddlewareProtocol.TCP;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if(protocol.equals(MiddlewareProtocol.TCP)) {
			ServerRequestHandlerTCP seHandlerTCP = new ServerRequestHandlerTCP(1300);
			seHandlerTCP.receive();
		} else {
			ServerRequestHandlerUDP seHandlerUDP = new ServerRequestHandlerUDP(1300);
			seHandlerUDP.receive();
		}
	}
}
