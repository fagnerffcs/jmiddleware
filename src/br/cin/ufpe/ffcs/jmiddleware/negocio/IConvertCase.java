package br.cin.ufpe.ffcs.jmiddleware.negocio;

public interface IConvertCase {
	public <T> String convertToUpper(String mensagem);
	public <T> String convertToLower(String mensagem);
}
