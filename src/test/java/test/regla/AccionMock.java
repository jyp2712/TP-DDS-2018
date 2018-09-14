package test.regla;


import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.regla.Accion;

public class AccionMock extends Accion {
	
	
	
	private int ejecuciones = 0;
	
	public AccionMock(DispositivoInteligente dispositivo) {
		super(dispositivo);
		// TODO Auto-generated constructor stub
	}
	
	public int getEjecuciones( ) {
		return this.ejecuciones;
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		this.ejecuciones += 1;
	}
	
}
