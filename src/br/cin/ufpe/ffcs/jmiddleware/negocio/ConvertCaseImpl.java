package br.cin.ufpe.ffcs.jmiddleware.negocio;

public class ConvertCaseImpl implements IConvertCase {
	
	public String convertToUpper(String message) {
		return message.toUpperCase();
	}
	
	public String convertToLower(String message) {
		return message.toLowerCase();
	}

}
