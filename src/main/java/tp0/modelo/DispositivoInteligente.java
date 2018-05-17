package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoInteligente{

	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;

	private Estado estado;

	@JsonCreator
	public DispositivoInteligente(@JsonProperty("nombre generico") String nombreGenerico, 
			@JsonProperty("KW/H") double KwXHora) {
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
		this.KwXHora = kwXHora;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Boolean estaEncendido() {
		return this.estado.estaEncendido();
	}


	public Boolean estaApagado() {
		return !this.estado.estaEncendido();
	}
	
	public void apagarse() {
		this.estado.apagarse(this);
	}

	public void encenderse() {
		this.estado.encenderse(this);
	}
	
	public void modoAhorroEnergia() {
		this.estado.modoAhorroEnergia(this);
	}
	
	public double consumo() {
		return this.KwXHora;
	}

	public Boolean esInteligente() {
		return true;
	}
	
	public double energiaConsumida(Hours horas) {
		return this.KwXHora * horas.getHours();
	}
	
	public double consumoTotal(DateTime periodo) {
		return this.KwXHora * Hours.hoursBetween(DateTime.now(), periodo).getHours();
	}
	
	public int otorgarPuntos() {
		return 15;
	}

}
