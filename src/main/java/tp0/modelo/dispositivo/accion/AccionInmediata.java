package tp0.modelo.dispositivo.accion;

import tp0.modelo.dispositivo.Dispositivo;

public abstract class AccionInmediata implements Accion {
	
	private Dispositivo dispositivo;
	
	public AccionInmediata(Dispositivo dispositivo) {
		this.setDispositivo(dispositivo);
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {
		// TODO Auto-generated method stub
	}

}
