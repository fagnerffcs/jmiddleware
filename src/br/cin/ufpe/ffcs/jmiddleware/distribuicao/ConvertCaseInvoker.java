package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp.ServerRequestHandlerTCP;
import br.cin.ufpe.ffcs.jmiddleware.negocio.ConvertCaseImpl;

public class ConvertCaseInvoker {
	
	public void invoke() throws IOException, ClassNotFoundException {
		ServerRequestHandlerTCP srhTcp = new ServerRequestHandlerTCP(1300);
		Marshaller marshaller = new Marshaller();
		ConvertCaseImpl convertCaseImpl = new ConvertCaseImpl();

		while(true) {
			//receive data
			byte[] receivedMsgBytes = srhTcp.receive();
			
			//unmarshall
			PacketMessage packetRequest = marshaller.unmarshall(receivedMsgBytes);
			String operation = packetRequest.getBody().getRequestHeader().getOperation();
			String convertedMsg = "", par1 = "";
			
			//demux request
			switch (operation) {
			case "convertToUpper":
				par1 = (String) packetRequest.getBody().getRequestBody().getBody().get(0);
				convertedMsg = convertCaseImpl.convertToUpper(par1);
				break;
			case "convertToLower":
				par1 = (String) packetRequest.getBody().getRequestBody().getBody().get(0);
				convertedMsg = convertCaseImpl.convertToLower(par1);
				break;
			}
			
			//assembly packet
			ReplyHeader replyHeader = new ReplyHeader("", packetRequest.getBody().getRequestHeader().getRequestId(), 1);
			ReplyBody replyBody = new ReplyBody(convertedMsg);
			MessageHeader messageHeader = new MessageHeader("MIOP", 1, true, MessageType.RESPONSE);
			MessageBody messageBody = new MessageBody(null, null, replyHeader, replyBody);
			PacketMessage replyMessage = new PacketMessage(messageHeader, messageBody);
			
			//marshall
			byte[] msgToClient = marshaller.marshall(replyMessage);
			
			srhTcp.send(msgToClient);
		}
	}
}