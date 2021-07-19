package br.cin.ufpe.ffcs.jmiddleware.model;

import java.io.Serializable;

public class MessageHeader implements Serializable {
	private static final long serialVersionUID = 608178304999372877L;
	private String magic;
	private int version;
	private boolean byteOrder;
	private MessageType messageType;
	
	public String getMagic() {
		return magic;
	}
	public void setMagic(String magic) {
		this.magic = magic;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isByteOrder() {
		return byteOrder;
	}
	public void setByteOrder(boolean byteOrder) {
		this.byteOrder = byteOrder;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public MessageHeader(String magic, int version, boolean byteOrder, MessageType messageType) {
		super();
		this.magic = magic;
		this.version = version;
		this.byteOrder = byteOrder;
		this.messageType = (messageType);
	}
	public MessageHeader() {
		super();
	}

	@Override
	public String toString() {
		return "MessageHeader [magic=" + magic + ", version=" + version + ", byteOrder=" + byteOrder + ", messageType="
				+ messageType + "]";
	}
}
