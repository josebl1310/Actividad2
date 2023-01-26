package Puente;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		//Creo mis coches con una direccion aleatoria y les paso el monitor que los controla
		MonitorPuente puente = new MonitorPuente();
		Random rnd = new Random();
		
		for (int i =0; i<10 ; i++) {
			new Coche(puente,rnd.nextBoolean());
		}
		
		
		
		// TODO Auto-generated method stub

	}

}
