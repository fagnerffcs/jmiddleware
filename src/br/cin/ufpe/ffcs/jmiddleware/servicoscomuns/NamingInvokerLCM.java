package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.Marshaller;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MessageBody;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MessageHeader;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MessageType;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.PacketMessage;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ReplyBody;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ReplyHeader;
import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp.ServerRequestHandlerTCP;

public class NamingInvokerLCM extends AbstractNamingInvoker {

	public void invoke() throws IOException, ClassNotFoundException {
		ServerRequestHandlerTCP srhTcp = new ServerRequestHandlerTCP(1313);
		Marshaller marshaller = new Marshaller();
		LifeCycleManager.createFactory(10, NamingImpl.class.getCanonicalName());

		while(true) {
			//receive data
			byte[] receivedMsgBytes = srhTcp.receive();

			//unmarshall
			PacketMessage packetRequest = marshaller.unmarshall(receivedMsgBytes);
			String operation = packetRequest.getBody().getRequestHeader().getOperation();
			Object result = null;

			//invoke pool from LCM
			NamingImpl namingImpl = (NamingImpl) LifeCycleManager.getInstance();
			
			//demux request
			switch (operation) {
			case "register":
				result = namingImpl.register(packetRequest.getBody().getRequestBody().getBody().get(0), 
											 packetRequest.getBody().getRequestBody().getBody().get(1));
				break;
			case "lookup":
				result = namingImpl.lookup(packetRequest.getBody().getRequestBody().getBody().get(0));
				break;
			case "list":
				result = namingImpl.list();
				break;
			}
			
			//returns another slot to LCM
			LifeCycleManager.releaseInstance();

			//assembly packet
			ReplyHeader replyHeader = new ReplyHeader("", packetRequest.getBody().getRequestHeader().getRequestId(), 1);
			ReplyBody replyBody = new ReplyBody(result);
			MessageHeader messageHeader = new MessageHeader("NamingOP", 1, true, MessageType.RESPONSE);
			MessageBody messageBody = new MessageBody(null, null, replyHeader, replyBody);
			PacketMessage replyMessage = new PacketMessage(messageHeader, messageBody);

			//marshall
			byte[] msgToClient = marshaller.marshall(replyMessage);

			//send data
			srhTcp.send(msgToClient);
		}		
	}
}