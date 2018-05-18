package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public interface Estado {

	default Boolean estaEncendido() {
		return false;
	};

	default void cambiarAEncendido(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	};

	default void cambiarAAhorroDeEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroEnergia());
	};

	default void cambiarAApagado(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Apagado());
	};

	public void encender(DispositivoInteligente dispositivo);

	public void apagar(DispositivoInteligente dispositivo);

	public void ahorrarEnergia(DispositivoInteligente dispositivo);

}
