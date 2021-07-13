package br.cin.ufpe.ffcs.jmiddleware.distribuicao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.cin.ufpe.ffcs.jmiddleware.model.RemoteObject;

public class Invoker {
	
	private ServerSocket serverSocket = null;
	private Socket conn = null;
	private int porta;

	public Invoker(int porta) {
		super();
		this.porta = porta;
	}

	public void invoke() throws IOException {
        ObjectOutputStream saida;
        ObjectInputStream entrada;
        boolean endConnection = false;
        String convertedMessage = "";
        
		try {
			serverSocket = new ServerSocket(porta);
			
			while(!endConnection) {
				System.out.println("Ouvindo na porta: " + this.porta);
				
				conn = serverSocket.accept();
				
				System.out.println("Conexao estabelecida com: " + conn.getInetAddress().getHostAddress());
				
                saida = new ObjectOutputStream(conn.getOutputStream());
                entrada = new ObjectInputStream(conn.getInputStream());
                
                do {
    				Marshaller marshaller = new Marshaller();
    				int tamanho = entrada.readInt();
    				byte[] unmashalledMsg = entrada.readNBytes(tamanho);
    				String i = marshaller.unmarshall(unmashalledMsg);
    				
    				RemoteObject remoteObject = new RemoteObject();
    				convertedMessage = remoteObject.convertToUpper(i);
    				
    				saida.writeObject(convertedMessage);
    				saida.flush();
                } while(!convertedMessage.equalsIgnoreCase("close"));

                System.out.println("Conexao encerrada pelo cliente.");
                endConnection = true;
                entrada.close();
                saida.close();
                conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
				
	}

}
