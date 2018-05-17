package tp0.modelo;

public interface Estado {
	public default boolean estaEncendido() {
		return true;
	}
	public default void apagarse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new EstadoApagado());
	}
	public default void encenderse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new EstadoEncendido());
	}
	public default void ahorrarEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new EstadoAhorroEnergia());
	}
}
