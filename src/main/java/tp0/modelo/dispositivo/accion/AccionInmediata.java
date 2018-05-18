package tp0.modelo.dispositivo.accion;

import tp0.modelo.dispositivo.*;

public abstract class AccionInmediata implements Accion {
	
	private DispositivoInteligente dispositivo;
	
	public AccionInmediata(DispositivoInteligente dispositivo) {
		this.setDispositivo(dispositivo);
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {
		// TODO Auto-generated method stub
	}

}
