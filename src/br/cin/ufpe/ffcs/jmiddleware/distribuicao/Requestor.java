package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp.ClientRequestHandlerTCP;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.udp.ClientRequestHandlerUDP;
import br.cin.ufpe.ffcs.jmiddleware.model.MiddlewareProtocol;

public class Requestor {
	
	private IClientRequestHandler crh;
	
	public Requestor(MiddlewareProtocol protocol, String host, int porta) {
		super();
		try {
			if(protocol.equals(MiddlewareProtocol.TCP)) {
				this.crh = (IClientRequestHandler) new ClientRequestHandlerTCP(host, porta);
			} else if(protocol.equals(MiddlewareProtocol.UDP)) {
				this.crh = (IClientRequestHandler) new ClientRequestHandlerUDP(host, porta);			
			}
		} catch(Exception e) {
			
		}
	}

	public Requestor() {
		super();
	}
	
	public void send(String message) throws IOException, InterruptedException {
		Marshaller marshaller = new Marshaller();
		this.crh.send(marshaller.marshall(message));
	}
	
	public String receive() throws IOException, InterruptedException {
		Marshaller marshaller = new Marshaller();
		return marshaller.unmarshall(this.crh.receive());
	}	

}
