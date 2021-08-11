package br.cin.ufpe.ffcs.jmiddleware.test;

import java.io.IOException;

import br.cin.ufpe.ffcs.jmiddleware.Client;

public class EvaluationLCM {

	private static int THREAD_SIZE = 100;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Runnable runnable = () -> { 
			Client client = new Client();
			try {
				long tempoInicial = System.currentTimeMillis();
				client.iniciarCliente("frase com caixa alta");
				System.out.println(System.currentTimeMillis() - tempoInicial);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		};
		
		try {
			synchronized (runnable) {
				for (int i = 0; i < THREAD_SIZE; i++) {
					Thread thread = new Thread(runnable);
					thread.start();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}