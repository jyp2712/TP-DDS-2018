package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dispositivo {

	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;

	@JsonProperty
	protected boolean estado;

	@JsonCreator
	public Dispositivo(@JsonProperty("nombre generico") String nombreGenerico, @JsonProperty("KW/H") double KwXHora,
			@JsonProperty("estado") boolean estado) {
		this.nombreGenerico = nombreGenerico;
		this.KwXHora = KwXHora;
		this.estado = estado;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

/*	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}*/

	public double getKwXHora() {
		return KwXHora;
	}

/*	public void setKwXHora(double kwXHora) {
		KwXHora = kwXHora;
	}*/

	public boolean getEstado() {
		return estado;
	}

	// SM: Por ahora supongo que se puede cambiar el estado. Todavia no sabemos como...
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	// SM: Un poco raro el pasamanos pero es para que el cliente se desacople de
	// como esta implementado el estado en el dispositivo
	public boolean estaEncendido() {
		return this.getEstado();
	}

}
