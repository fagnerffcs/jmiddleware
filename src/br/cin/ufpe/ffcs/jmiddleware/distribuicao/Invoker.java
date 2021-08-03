package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.negocio.ConvertCaseImpl;

public class Invoker {
	
	public byte[] invoke(byte[] m) throws IOException {
		Marshaller marshaller = new Marshaller();
		PacketMessage i = marshaller.unmarshall(m);
		String convertedMessage = "";
		
		try {
			String operation = i.getBody().getRequestHeader().getOperation();
			String params = (String) i.getBody().getRequestBody().getBody().get(0);
			
			ConvertCaseImpl remoteObject = new ConvertCaseImpl();
			switch (operation) {
			case "convertToUpper":
				convertedMessage = remoteObject.convertToUpper(params);
				break;
			case "convertToLower":
				convertedMessage = remoteObject.convertToLower(params);
				break;
			default:
				break;
			}
			
			ReplyHeader replyHeader = new ReplyHeader();
			ReplyBody replyBody = new ReplyBody(convertedMessage);
			MessageHeader messageHeader = new MessageHeader("MIOP", 1, false, MessageType.RESPONSE);
			MessageBody messageBody = new MessageBody(null, null, replyHeader, replyBody);
			PacketMessage message = new PacketMessage(messageHeader, messageBody);    				
			
			return marshaller.marshall(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}
}