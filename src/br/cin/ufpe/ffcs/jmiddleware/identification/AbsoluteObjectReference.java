package br.cin.ufpe.ffcs.jmiddleware.identification;

public class AbsoluteObjectReference {
	private int porta;
	private String host;
	private String objectName;
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public AbsoluteObjectReference() {
		super();
	}
	public AbsoluteObjectReference(int porta, String host, String objectName) {
		super();
		this.porta = porta;
		this.host = host;
		this.objectName = objectName;
	}

}
