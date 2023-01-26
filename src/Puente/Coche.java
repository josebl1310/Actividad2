package Puente;

import java.util.Random;

public class Coche extends Thread{
	
	//Creo el objeto coche con los tres estados posibles
	
	private enum STATE {ESPERANDO, CRUZANDO, LIBRE};
	private STATE state;
	private int id;
	private static int totalCoches;
	private MonitorPuente monitor;
	public boolean paso; ///True=norte    False = sur

	
	//Al coche le paso el monitor y una variable booleana con la posicion para ver si viene del sur o del norte
	public Coche (MonitorPuente monitor, boolean dir) {
		this.id= ++totalCoches;
		this.paso=dir;
		this.monitor= monitor;
		state= STATE.ESPERANDO;
		start();
	}
	//Este metodo sirve para que el coche espere a que el puente este libre de coches cruzando en el sentido contrario
	public void esperandoPaso () {

		if (this.paso==true) 
			System.out.println("Esperando en el paso norte el coche con el ID: " +this.id);
		else 
			System.out.println("Esperando en el paso sur el coche con el ID: " +this.id);
		Random rnd = new Random();
		
		int tiempoCruce = rnd.nextInt(250-50 +1) + 50;
		
		try {
			sleep(tiempoCruce);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		monitor.ObtenerPaso(this.paso);
		state = STATE.CRUZANDO;
		
		
	}
	//Este metodo sirve para controlar lo que tarda un coche en cruzar el puente y después indicar al monitor que lo ha cruzado
	public void cruzandoPuente() {
		if (this.paso)
			System.out.println("El coche con el ID " +this.id+ " esta cruzando el puente desde el norte");
		else
			System.out.println("El coche con el ID " +this.id+ " esta cruzando el puente desde el sur");
		Random rnd = new Random();
		
		int tiempoCruce = rnd.nextInt(400-50 +1) + 50;
		try {
			sleep(tiempoCruce);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El coche con el ID " +this.id+ " ha cruzado el puente");
		
		monitor.PasoCompleto(this.paso);
		
		state = STATE.LIBRE;
	}
	//Nuestro metodo run ira cambiando entre los diferentes estados del coche
	public void run() {
		boolean salida=true;
		while(salida) {
			switch (state) {
				case ESPERANDO:
					esperandoPaso();
					break;
				case CRUZANDO:
					cruzandoPuente();
					break;
				case LIBRE:
					salida=false;
					break;
			}
		}
	}
	
}
