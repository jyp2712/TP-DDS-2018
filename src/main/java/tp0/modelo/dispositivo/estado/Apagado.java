package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class Apagado extends Estado {

	// TODO: Consultar al dispositivo su estado en vez de guardarlo internamente
	public Boolean estaEncendido() {
		return false;
	}

	@Override
	public void apagar(DispositivoInteligente dispositivo) {
	}
}
