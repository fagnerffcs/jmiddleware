package br.cin.ufpe.ffcs.jmiddleware;

import java.util.logging.Logger;

import br.cin.ufpe.ffcs.jmiddleware.distribuicao.AbstractConvertInvoker;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseInvoker;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseInvokerLCM;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.ConvertCaseProxy;
import br.cin.ufpe.ffcs.jmiddleware.distribuicao.MiddlewareProtocol;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.LifeCycleMode;
import br.cin.ufpe.ffcs.jmiddleware.servicoscomuns.NamingProxy;

public class ConvertCaseServer {
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private static final LifeCycleMode LI_MODE = LifeCycleMode.ENABLED;
	
	public static void main(String[] args) throws Throwable {
		//create a built-in proxy do naming service
		NamingProxy namingProxy = new NamingProxy("localhost", 1313, MiddlewareProtocol.TCP);
		
		//create a instance of ConverCaseProxy
		ConvertCaseProxy convertCaseProxy = new ConvertCaseProxy("localhost", 1300, MiddlewareProtocol.TCP);
		
		//register service
		if(namingProxy.register("ConvertCase", convertCaseProxy)) {
			//control loop to middleware
			LOGGER.info("Servidor iniciado com sucesso.");
			AbstractConvertInvoker invoker;
			if(LI_MODE.equals(LifeCycleMode.DISABLED)) {
				invoker = new ConvertCaseInvoker();
			} else {
				invoker = new ConvertCaseInvokerLCM();
			}
			invoker.invoke();
		} else {
			LOGGER.info("Erro ao iniciar o servidor.");
		}
		
	}
}
