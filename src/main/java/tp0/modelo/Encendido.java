package tp0.modelo;

public class Encendido implements Estado {

	@Override
	public Boolean estaEncendido() {
		return true;
	}

	@Override
	public void encenderse(DispositivoInteligente dispositivo) {
	}

	@Override
	public void modoAhorroEnergia(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new AhorroEnergia());
	}

	@Override
	public void apagarse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Apagado());
	}

}
