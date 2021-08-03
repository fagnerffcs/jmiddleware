package br.cin.ufpe.ffcs.jmiddleware.infraestrutura.tcp;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import br.cin.ufpe.ffcs.jmiddleware.infraestrutura.IClientRequestHandler;

public class ClientRequestHandlerTCP implements IClientRequestHandler {
	
	private int porta;
	
	private Socket socket = null;
    private DataOutputStream saida;
    private DataInputStream entrada;
    
	public ClientRequestHandlerTCP(int porta) throws UnknownHostException, IOException {
		super();
		this.porta = porta;
	}
	
	public byte[] sendReceive(byte[] msg) throws UnknownHostException, IOException {
		InetAddress host = InetAddress.getLocalHost();
		socket = new Socket(host.getHostAddress(), this.porta);
		
		//send data to using TCP Channel
		saida = new DataOutputStream(socket.getOutputStream());
		saida.writeInt(msg.length);
		saida.write(msg);
		saida.flush();
		
		//receive data
		byte[] retorno = null;
		entrada = new DataInputStream(socket.getInputStream());
		int tamanho = entrada.readInt();
		entrada.read(retorno, 0, tamanho);
		
		//close resources
		saida.close();
		entrada.close();
		
		return retorno;
	}

}