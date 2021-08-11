package br.cin.ufpe.ffcs.jmiddleware.servicoscomuns;

import java.lang.reflect.InvocationTargetException;

public class LifeCycleManager {
	
	private static int POOL_SIZE;
	private static Object[] pool;
	
	//controlar o indice array utilizado
	private static int atualIndex = -1;
	private static int avalaibleIndices;
	
	//factoryMethod to initialize POOL_SIZE and pool
	public static void createFactory(int size, String objName) {
		POOL_SIZE = size;
		pool = new Object[POOL_SIZE];
		avalaibleIndices = POOL_SIZE;
		for (int i = 0; i < pool.length; i++) {
			try {
				pool[i] = Class.forName(objName).getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object getInstance() {
		int pos = LifeCycleManager.getNextIndex();
		try {
			while(avalaibleIndices < 0) {
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			
		}
		return pool[pos];
	}
	
	private static int getNextIndex() {
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
	
	//increase avalaibleIndices
	public static void releaseInstance() {
		avalaibleIndices++;
	}

}