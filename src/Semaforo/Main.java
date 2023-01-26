package Semaforo;

public class Main {
	//Se crea los productores y los consumidores y se ejecuta el programa
	public static int numConsumers = 40;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new Productor();
		for (int i=0 ; i<numConsumers ; i++)
			new Consumidor();
	}

}
