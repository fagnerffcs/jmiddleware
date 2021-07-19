package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

public class Invocation {
	private Request request;

	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Invocation(Request request) {
		super();
		this.request = request;
	}
	public Invocation() {
		super();
	}	
}
