package test.regla;

import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.accion.AccionInmediata;

public class AccionInmediataMock extends AccionInmediata {
	
	private int ejecuciones = 0;
	
	public AccionInmediataMock(DispositivoInteligente dispositivo) {
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
