package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;

public class MessageBody implements Serializable {
	private static final long serialVersionUID = -5439164398905936289L;

	private RequestHeader requestHeader;
	private RequestBody requestBody;
	private ReplyHeader replyHeader;
	private ReplyBody replyBody;
	
	public MessageBody(RequestHeader reqHeader, RequestBody reqBody, ReplyHeader repHeader, ReplyBody repBody) {
		this.setRequestHeader(reqHeader);
		this.setRequestBody(reqBody);
		this.setReplyHeader(repHeader);
		this.setReplyBody(repBody);
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public ReplyHeader getReplyHeader() {
		return replyHeader;
	}

	public void setReplyHeader(ReplyHeader replyHeader) {
		this.replyHeader = replyHeader;
	}

	public ReplyBody getReplyBody() {
		return replyBody;
	}

	public void setReplyBody(ReplyBody replyBody) {
		this.replyBody = replyBody;
	}

	@Override
	public String toString() {
		return "MessageBody [requestHeader=" + requestHeader + ", requestBody=" + requestBody + ", replyHeader="
				+ replyHeader + ", replyBody=" + replyBody + "]";
	}
	
}
