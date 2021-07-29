package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp.ClientRequestHandlerTCP;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.udp.ClientRequestHandlerUDP;

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
	
	public String invoke(Invocation invocation) throws IOException, InterruptedException, ClassNotFoundException {
		//construcao do messageHeader
		MessageHeader messageHeader = new MessageHeader("MyMid", 1, false, MessageType.REQUEST);
		//construcao do request (header + body)
		RequestHeader requestHeader = new RequestHeader("Context", 1000, true, 2000, invocation.getRequest().getOperationName());
		RequestBody requestBody = new RequestBody(invocation.getRequest().getParams());
		//construcao do messageBody(reqHeader + reqBody)
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);
		//construcao da mensagem a ser enviada (messageHeader + messageBody)
		PacketMessage message = new PacketMessage(messageHeader, messageBody);
		Marshaller marshaller = new Marshaller();
		byte[] marshalledMessage = marshaller.marshall(message);
		this.crh.send(marshalledMessage);
		byte[] unmarshalledMessage = this.crh.receive();
		PacketMessage repliedMsg = marshaller.unmarshall(unmarshalledMessage);
		return repliedMsg !=null ? repliedMsg.getBody().getReplyBody().getOperationResult().toString() : null;
	}	

}
