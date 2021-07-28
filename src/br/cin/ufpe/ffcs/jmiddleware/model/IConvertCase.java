package br.cin.ufpe.ffcs.jmiddleware.model;

public interface IConvertCase {
	public <T> String convertToUpper(String mensagem);
	public <T> String convertToLower(String mensagem);
}
