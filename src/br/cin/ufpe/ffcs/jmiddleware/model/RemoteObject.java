package br.cin.ufpe.ffcs.jmiddleware.model;

public class RemoteObject implements IConvertCase {
	
	public String convertToUpper(String message) {
		return message.toUpperCase();
	}
	
	public String convertToLower(String message) {
		return message.toLowerCase();
	}

}
