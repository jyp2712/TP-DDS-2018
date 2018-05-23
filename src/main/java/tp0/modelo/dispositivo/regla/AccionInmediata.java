package tp0.modelo.dispositivo.regla;

import tp0.modelo.dispositivo.*;

public abstract class AccionInmediata implements Accion {
	
	private DispositivoInteligente dispositivo;
	
	public AccionInmediata(DispositivoInteligente dispositivo) {
		this.setDispositivo(dispositivo);
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	private void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {
		this.getDispositivo().getDispositivoFisico().ejecutar(this);
	}
}
