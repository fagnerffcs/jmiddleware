package br.cin.ufpe.ffcs.jmiddleware.model;

import java.io.Serializable;

public class ReplyBody implements Serializable {
	private static final long serialVersionUID = 6325108088750698419L;

	private Object operationResult;

	public ReplyBody(Object opResult){
		this.operationResult = opResult;
		
	}
	public Object getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(Object operationResult) {
		this.operationResult = operationResult;
	}
	@Override
	public String toString() {
		return "ReplyBody [operationResult=" + operationResult + "]";
	}
}
