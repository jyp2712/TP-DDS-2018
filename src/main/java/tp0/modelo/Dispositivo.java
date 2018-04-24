package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dispositivo {
	
	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;
	
	protected boolean estado;
	
	@JsonCreator
	public Dispositivo(
			@JsonProperty("nombre generico") String nombreGenerico,
			@JsonProperty("KW/H") double KwXHora
			) {
		this.nombreGenerico = nombreGenerico;
		this.KwXHora = KwXHora;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
