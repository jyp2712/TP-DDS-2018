package tp0.modelo.dispositivo.regla;

import tp0.modelo.dispositivo.*;

public abstract class Accion {
	
	private DispositivoInteligente dispositivo;
	
	public Accion(DispositivoInteligente dispositivo) {
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
