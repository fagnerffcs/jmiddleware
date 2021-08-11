package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

public class LifeCycleManager {
	
	private static int POOL_SIZE;
	private static NamingImpl[] pool;
	
	//controlar o indice array utilizado
	private static int atualIndex = -1;
	private static int avalaibleIndices;
	
	//factoryMethod to initialize POOL_SIZE and pool
	public static void createFactory(int size) {
		POOL_SIZE = size;
		pool = new NamingImpl[POOL_SIZE];
		avalaibleIndices = POOL_SIZE;
		for (int i = 0; i < pool.length; i++) {
			pool[i] = new NamingImpl();
		}
	}
	
	public static NamingImpl getInstance(int pos) {
		try {
			synchronized (pool) {
				while(avalaibleIndices < 0) {
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {
			
		}
		return pool[pos];
	}
	
	public static int getNextIndex() {
		avalaibleIndices--;
		if(atualIndex==-1) {
			return 0;
		} else {
			if(avalaibleIndices==0) {
				atualIndex = 9;
			} else {
				atualIndex++;
			}
			return atualIndex;
		}
	}
	
	public static void copyRegisteredObjectToPool(NamingImpl namingImpl) {
		for (int i = 0; i < pool.length; i++) {
			pool[i] = namingImpl;
		}
	}

	//increase
	public static void freeInstance() {
		avalaibleIndices++;
	}

}