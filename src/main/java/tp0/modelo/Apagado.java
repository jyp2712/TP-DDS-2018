package tp0.modelo;

public class Apagado implements Estado {

	@Override
	public Boolean estaEncendido() {
		return false;
	}

	@Override
	public void encenderse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	@Override
	public void modoAhorroEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroEnergia());
	}

	@Override
	public void apagarse(DispositivoInteligente dispositivo) {}

}
