package br.cin.ufpe.ffcs.jmiddleware.model;

public interface IChat {
	
	public void enviarMensagem(String mensagem);
	public String receberMensagem();
}
