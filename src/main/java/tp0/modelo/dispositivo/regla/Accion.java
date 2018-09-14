package tp0.modelo.dispositivo.regla;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tp0.modelo.PersistentObject;
import tp0.modelo.dispositivo.*;

@Entity
public class Accion extends PersistentObject{
	
	@ManyToOne(optional = false)
	protected DispositivoInteligente dispositivo;

	public Accion() {}
	
	public Accion(DispositivoInteligente dispositivo) {
		this.setDispositivo(dispositivo);
	}

	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}

	private void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}

	public void ejecutar() {
		this.getDispositivo().getDispositivoFisico().ejecutar(this);
	}
}
