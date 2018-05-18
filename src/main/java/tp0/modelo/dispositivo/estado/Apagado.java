package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class Apagado implements Estado {

	public void encender(DispositivoInteligente dispositivo) {
		this.cambiarAEncendido(dispositivo);
	}

	@Override
	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
		this.cambiarAAhorroDeEnergia(dispositivo);
	}

	@Override
	public void apagar(DispositivoInteligente dispositivo) {
	}

}
