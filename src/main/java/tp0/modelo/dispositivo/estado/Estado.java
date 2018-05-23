package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public abstract class Estado {

	// TODO: Consultar al dispositivo su estado en vez de guardarlo internamente
	public abstract Boolean estaEncendido();

	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().encender();
		dispositivo.setEstado(new Encendido());
	};

	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().apagar();
		dispositivo.setEstado(new Apagado());
	};

	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().ahorrarEnergia();
		dispositivo.setEstado(new AhorroEnergia());
	}
}
