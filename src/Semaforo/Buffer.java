package Semaforo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Buffer {
	
	//Creo el buffer de recursos y los semaforos para controlar que el stock este disponible y que no sobrepase el tamaño del buffer.
	
	private static ConcurrentLinkedQueue<Integer> store = new ConcurrentLinkedQueue<Integer>();
	public static final int bsize=Integer.MAX_VALUE;
	private static Semaphore sNoVacio = new Semaphore(0,true);
	private static Semaphore sNoLleno = new Semaphore(bsize,true);
	
	public static ConcurrentLinkedQueue<Integer> getStore() {
		return store;
	}
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}

	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}
}
