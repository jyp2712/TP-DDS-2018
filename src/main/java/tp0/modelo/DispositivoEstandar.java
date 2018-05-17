package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoEstandar implements TipoDispositivo {

	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;

	@JsonCreator
	public DispositivoEstandar(@JsonProperty("nombre generico") String nombreGenerico, @JsonProperty("KW/H") double KwXHora) {
		setNombreGenerico(nombreGenerico);
		setKwXHora(KwXHora);
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	private void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public double getKwXHora() {
		return KwXHora;
	}

	private void setKwXHora(double kwXHora) {
		KwXHora = kwXHora;
	}

	public boolean estaEncendido() {
		throw new ExceptionNoSoportado();
	}

	public boolean estaApagado() {
		throw new ExceptionNoSoportado();
	}

	public float energiaConsumida() {
		throw new ExceptionNoSoportado();
	}

	public float consumoTotal() {
		throw new ExceptionNoSoportado();
	}

	public void encenderse() {
		throw new ExceptionNoSoportado();
	}

	public void apagarse() {
		throw new ExceptionNoSoportado();	
	}

	public void ahorrarEnergia() {
		throw new ExceptionNoSoportado();
	}
}
