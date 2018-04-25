package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dispositivo {
	
	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;

	@JsonProperty
	protected boolean encendido;
	
	@JsonCreator
	public Dispositivo(
			@JsonProperty("nombre generico") String nombreGenerico,
			@JsonProperty("KW/H") double KwXHora,
			@JsonProperty("esta encendido") boolean encendido
			) {
		this.nombreGenerico = nombreGenerico;
		this.KwXHora = KwXHora;
		this.encendido = encendido;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public double getKwXHora() {
		return KwXHora;
	}

	public void setKwXHora(double kwXHora) {
		KwXHora = kwXHora;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	public boolean estaEncendido() {
		return encendido;
	}
	
}
