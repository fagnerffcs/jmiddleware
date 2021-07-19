package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.model.Message;
import br.cin.ufpe.ffcs.jmiddleware.model.MessageBody;
import br.cin.ufpe.ffcs.jmiddleware.model.MessageHeader;
import br.cin.ufpe.ffcs.jmiddleware.model.MessageType;
import br.cin.ufpe.ffcs.jmiddleware.model.RemoteObject;
import br.cin.ufpe.ffcs.jmiddleware.model.ReplyBody;
import br.cin.ufpe.ffcs.jmiddleware.model.ReplyHeader;

public class Invoker {
	
	public byte[] invoke(byte[] m) throws IOException {
		Marshaller marshaller = new Marshaller();
		Message i = marshaller.unmarshall(m);
		String convertedMessage = "";
		
		try {
			String operation = i.getBody().getRequestHeader().getOperation();
			String params = i.getBody().getRequestBody().getBody().get(0);
			
			RemoteObject remoteObject = new RemoteObject();
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
			MessageHeader messageHeader = new MessageHeader("MyMid", 1, false, MessageType.RESPONSE);
			MessageBody messageBody = new MessageBody(null, null, replyHeader, replyBody);
			Message message = new Message(messageHeader, messageBody);    				
			
			return marshaller.marshall(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return m;
	}

}
