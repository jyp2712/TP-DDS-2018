package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class Encendido implements Estado {

	public Boolean estaEncendido() {
		return true;
	}

	public void encender(DispositivoInteligente dispositivo) {
	}

	public void apagar(DispositivoInteligente dispositivo) {
		this.cambiarAApagado(dispositivo);
	}

	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
		this.cambiarAAhorroDeEnergia(dispositivo);
	}

}
