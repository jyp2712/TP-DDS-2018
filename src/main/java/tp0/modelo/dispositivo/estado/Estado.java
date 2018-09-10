package tp0.modelo.dispositivo.estado;

import tp0.modelo.dispositivo.DispositivoInteligente;

public enum Estado {
	
	APAGADO {
		@Override
		public void apagar(DispositivoInteligente dispositivo) {}
	},
	ENCENDIDO {
		@Override
		public void encender(DispositivoInteligente dispositivo) {}
	},
	AHORROENERGIA {
		@Override
		public void ahorrarEnergia(DispositivoInteligente dispositivo) {}
	};
	
 	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().encender();
		dispositivo.setEstado(ENCENDIDO);
	}
	
	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().apagar();
		dispositivo.setEstado(APAGADO);
	};
	
 	public void ahorrarEnergia(DispositivoInteligente dispositivo) {
		dispositivo.getDispositivoFisico().ahorrarEnergia();
		dispositivo.setEstado(AHORROENERGIA);
	}
}