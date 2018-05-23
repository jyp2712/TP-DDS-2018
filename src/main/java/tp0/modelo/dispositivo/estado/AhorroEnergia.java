package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class AhorroEnergia extends Estado {

	// TODO: Consultar al dispositivo su estado en vez de guardarlo internamente
	public Boolean estaEncendido() {
		return true;
	}

	@Override
	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
	}
}
