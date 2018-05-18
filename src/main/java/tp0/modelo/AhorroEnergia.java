package tp0.modelo;

public class AhorroEnergia implements Estado {

	@Override
	public Boolean estaEncendido() {
		return true;
	}

	@Override
	public void encenderse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Encendido());
	}

	@Override
	public void modoAhorroEnergia(DispositivoInteligente dispositivo) {
	}

	@Override
	public void apagarse(DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new Apagado());
	}

}
