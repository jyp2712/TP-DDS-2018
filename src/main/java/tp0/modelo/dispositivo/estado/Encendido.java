package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public class Encendido extends Estado {

	// TODO: Consultar al dispositivo su estado en vez de guardarlo internamente
	public Boolean estaEncendido() {
		return true;
	}

	@Override
	public void encender(DispositivoInteligente dispositivo) {
	}
}
