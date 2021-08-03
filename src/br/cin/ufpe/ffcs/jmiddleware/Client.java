package br.cin.ufpe.ffcs.jmiddleware;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.NamingProxy;

public class Client {
	
	public static void main(String[] args) throws Throwable {
		NamingProxy namingProxy = new NamingProxy("localhost", 1313, MiddlewareProtocol.TCP);
		ConvertCaseProxy clientProxy = (ConvertCaseProxy) namingProxy.lookup("ConvertCase");
		System.out.println(clientProxy.convertToUpper("frase com caixa alta"));
		System.out.println(clientProxy.convertToLower("FRASE COM CAIXA BAIXA"));
	}
}