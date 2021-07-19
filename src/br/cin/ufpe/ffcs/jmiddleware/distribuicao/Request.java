package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.util.ArrayList;

public class Request {
	private String operationName;
	private ArrayList<String> params;
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public ArrayList<String> getParams() {
		return params;
	}
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}
	public Request(String operationName, ArrayList<String> params) {
		super();
		this.operationName = operationName;
		this.params = params;
	}
	public Request() {
		super();
	}
}
