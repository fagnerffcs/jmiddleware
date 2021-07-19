package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import br.cin.ufpe.ffcs.jmiddleware.model.Message;

public class Marshaller {
	
	public byte[] marshall(Message msgToBeMarshalled) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(msgToBeMarshalled);

		return byteStream.toByteArray();
	}

	public Message unmarshall(byte[] msgToBeUnmarshalled) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
		ObjectInputStream objectStream = null;
		try {
			objectStream = new ObjectInputStream(byteStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			return (Message) objectStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
