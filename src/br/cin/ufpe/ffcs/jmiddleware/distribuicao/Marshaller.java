package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

public class Marshaller {
	
	public byte[] marshall(String messageUnmarshalled) {
		return messageUnmarshalled.getBytes();
	}

	public String unmarshall(byte[] messageMarshalled) {
		return new String(messageMarshalled);
	}
	
}
