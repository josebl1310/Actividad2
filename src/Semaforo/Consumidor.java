package Semaforo;

import java.util.Random;

import Semaforo.Buffer;

public class Consumidor extends Thread {
	
	public Consumidor(){
		start();
	}
	//La funcion consumir saca un elemento de la cola 
public void Consumir() {
		
	Random rnmNum = new Random();
	int sleepTime = rnmNum.nextInt(250-25+1);
	
	
	try {
		sleep(sleepTime);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Buffer.getStore().poll();
	
	}

public void run () {

	while(true) {
		
		// Se consumiran un numero aleatorio d recursos, solo se consumirar si hay suficientes unidades del mismo en la cola, si no esperará
		Random rnmNum = new Random();
		int numUnidades = rnmNum.nextInt(399)+1;
		int sleepTime = rnmNum.nextInt(250-25+1);
		
		if(Buffer.getStore().size()==0) {
			//System.out.println("El Buffer esta vacio, esperando al productor ");
		}
		if (numUnidades<Buffer.getStore().size()) {
		try {
		for (int i=0 ; i<numUnidades; i++)
				Buffer.getsNoVacio().acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0 ; i<numUnidades ; i++) {
		Consumir();
		Buffer.getsNoLleno().release();
		}
		System.out.println("Consumo " +numUnidades+ " unidades");
		}
		/*else
			System.out.println("No hay suficientes unidades en el Buffer, esperando al productor");*/
	}
}

}
