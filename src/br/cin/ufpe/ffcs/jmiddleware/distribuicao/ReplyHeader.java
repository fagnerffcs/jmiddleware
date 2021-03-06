package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;

public class ReplyHeader implements Serializable {
	private static final long serialVersionUID = 7581208418750898740L;

	private String serviceContext;
	private int requestId;
	private int replyStatus;
	public String getServiceContext() {
		return serviceContext;
	}
	public void setServiceContext(String serviceContext) {
		this.serviceContext = serviceContext;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(int replyStatus) {
		this.replyStatus = replyStatus;
	}
	public ReplyHeader(String serviceContext, int requestId, int replyStatus) {
		super();
		this.serviceContext = serviceContext;
		this.requestId = requestId;
		this.replyStatus = replyStatus;
	}
	public ReplyHeader() {
		super();
	}
	@Override
	public String toString() {
		return "ReplyHeader [serviceContext=" + serviceContext + ", requestId=" + requestId + ", replyStatus="
				+ replyStatus + "]";
	}
	
}
