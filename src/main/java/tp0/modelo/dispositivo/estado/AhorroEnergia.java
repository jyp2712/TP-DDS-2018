package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class AhorroEnergia implements Estado {

	public Boolean estaEncendido() {
		return true;
	}

	public void encender(DispositivoInteligente dispositivo) {
		this.cambiarAEncendido(dispositivo);
	}

	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
	}

	public void apagar(DispositivoInteligente dispositivo) {
		this.cambiarAApagado(dispositivo);
	}
}
