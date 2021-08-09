package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

public class LifeCycleManager {
	
	private static int POOL_SIZE;
	private static NamingImpl[] pool;
	
	//controlar o indice array utilizado
	private static int atualIndex = -1;
	private static int avalaibleIndices = POOL_SIZE;
	
	//factoryMethod to initialize POOL_SIZE and pool
	public static void createFactory(int size) {
		POOL_SIZE = size;
		pool = new NamingImpl[POOL_SIZE];
		for (int i = 0; i < pool.length; i++) {
			pool[i] = new NamingImpl();
		}
	}
	
	public static NamingImpl getInstance() {
		try {
			synchronized (pool) {
				while(avalaibleIndices > 0) {
					Thread.sleep(10000);
				}
			}
		} catch (InterruptedException e) {
			
		}
		int pos = getNextIndex();
		return pool[pos];
	}
	
	private static int getNextIndex() {
		avalaibleIndices--;
		if(atualIndex==-1) {
			return 0;
		} else {
			return atualIndex++;
		}
	}

	//increase
	public static void freeInstance() {
		avalaibleIndices++;
	}

}