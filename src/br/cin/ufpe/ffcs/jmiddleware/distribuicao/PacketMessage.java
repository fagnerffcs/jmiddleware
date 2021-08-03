package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.Serializable;

public class PacketMessage implements Serializable {
	private static final long serialVersionUID = 5176094953316465233L;
	private MessageHeader header;
    private MessageBody body;
    
	public MessageHeader getHeader() {
		return header;
	}
	public void setHeader(MessageHeader header) {
		this.header = header;
	}
	public MessageBody getBody() {
		return body;
	}
	public void setBody(MessageBody body) {
		this.body = body;
	}
	public PacketMessage(MessageHeader header, MessageBody body) {
		super();
		this.header = header;
		this.body = body;
	}
	public PacketMessage() {
		super();
	}
	@Override
	public String toString() {
		return "Message [header=" + header + ", body=" + body + "]";
	}
}
