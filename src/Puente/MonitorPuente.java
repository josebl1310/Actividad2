package Puente;

public class MonitorPuente {
	
	private boolean paso[] = new boolean[2];
	
	private int cochesCruzando;
	
	//El monitor controla por donde cruzan los coches y cuantos están cruzando.
	public MonitorPuente () {
		
		this.paso[0]=true; ///Norte
		this.paso[1]=true; ///Sur
		cochesCruzando=0;

		
	}
	
	public synchronized void ObtenerPaso (boolean p) {  ///True=norte   False= sur
		///El metodo obtenerPaso hara que los coches esperen a que no haya ningun coche cruzando en sentido opuesto
		if (p) {
		while (!paso[0]) {
			
			try {
			
				wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
				
		}
		paso[1]=false;
		}
		
		else {
			while (!paso[1]) {
				
				try {
					wait();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
					
					
			}
			paso[0]=false;
			
		}
		cochesCruzando++;
	}
	//Una vez que los coches crucen se dara el ok para que el resto de coches del sentido contrario puedan cruzar, solo se dará paso cuando el puente este vacio
	public synchronized void PasoCompleto (boolean p) {
		cochesCruzando--;
		if (cochesCruzando==0) {
		if (p) {
				
				paso[1]=true;
		}
		else {
				paso[0]=true;
			
		}
		}
		notify();
		
	}
}
