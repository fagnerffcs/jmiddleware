package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestBody implements Serializable {

	private static final long serialVersionUID = -433116932758592596L;

	private ArrayList<String> body;

	public ArrayList<String> getBody() {
		return body;
	}

	public void setBody(ArrayList<String> body) {
		this.body = body;
	}

	public RequestBody() {
		super();
	}

	public RequestBody(ArrayList<String> body) {
		super();
		this.body = body;
	}

	@Override
	public String toString() {
		return "RequestBody [body=" + body + "]";
	}
	
}
