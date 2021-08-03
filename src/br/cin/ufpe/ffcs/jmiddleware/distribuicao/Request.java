package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.util.ArrayList;

public class Request {
	private String operationName;
	private ArrayList<Object> params;
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public ArrayList<Object> getParams() {
		return params;
	}
	public void setParams(ArrayList<Object> params) {
		this.params = params;
	}
	public Request(String operationName, ArrayList<Object> params) {
		super();
		this.operationName = operationName;
		this.params = params;
	}
	public Request() {
		super();
	}
}
