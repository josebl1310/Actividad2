package Semaforo;

import java.util.Random;

import Semaforo.Buffer;

public class Productor extends Thread{
	
	public Productor() {
		start();
	}
	//El productor produce un recurso y lo mete en la cola
	
	public void Producir (Integer random) {
		Random rndNum = new Random();
		int sleepTime = rndNum.nextInt(700-25+1);
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Produzco " +random+ " unidades.");
		
		for (int i = 0 ; i<random ; i++) {
		Buffer.getStore().add(rndNum.nextInt(999)+1);
		}
	}
	
	public void run() {
		//Se producen un numero aleatorio de recursos cada vez, siempre que no se vaya a llenar el buffer con la produccion.
		while(true) {
			Random rndNum = new Random();
			int numUnidades = rndNum.nextInt(299)+1;
			
			if (Buffer.getStore().size() == Buffer.bsize) {
				System.out.println("El buffer esta lleno, esperando a que algún consumidor trabaje");
			}
			
			if (numUnidades + Buffer.getStore().size() < Buffer.bsize) {
			try {
				for (int i=0; i<numUnidades ; i++) {
				Buffer.getsNoLleno().acquire(rndNum.nextInt(999)+1);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Producir(numUnidades);
			for (int i=0; i<numUnidades ; i++) {
			Buffer.getsNoVacio().release();
			}
			}
		}
	}
}
