package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoInteligente implements TipoDispositivo {
	@JsonProperty
	protected Estado estado;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	// SM: Un poco raro el pasamanos pero es para que el cliente se desacople de
	// como esta implementado el estado en el dispositivo
	public boolean estaEncendido() {
		return this.getEstado().estaEncendido();
	}
	
	public boolean estaApagado() {
		return !this.getEstado().estaEncendido();
	}
	
	public float energiaConsumida() {
		return 0;
	}
	public float consumoTotal() {
		return 0;
	}

	public void encenderse() {
		getEstado().encenderse(this);
	}

	public void apagarse() {
		getEstado().apagarse(this);		
	}

	public void ahorrarEnergia() {
		getEstado().ahorrarEnergia(this);
	}
}
