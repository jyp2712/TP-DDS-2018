package tp0.modelo;

public interface Estado {

	Boolean estaEncendido();

	void encenderse(DispositivoInteligente dispositivo);

	void modoAhorroEnergia(DispositivoInteligente dispositivo);

	void apagarse(DispositivoInteligente dispositivo);

}
