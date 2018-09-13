package tp0.modelo.dispositivo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@DiscriminatorValue("estandar")
public class DispositivoEstandar extends Dispositivo {

	@JsonProperty
	protected double horasDeConsumo;

	@JsonCreator
	public DispositivoEstandar(@JsonProperty("nombre generico") String nombreGenerico,
			@JsonProperty("KW/H") double KwXHora, @JsonProperty("Horas de consumo") double horasDeConsumo) {
		this.nombreGenerico = nombreGenerico;
		setKwXHora(KwXHora);
		setHorasDeConsumo(horasDeConsumo);
		this.comenzarReporte();
	}

	private void setHorasDeConsumo(double horasDeConsumo) {
		this.horasDeConsumo = horasDeConsumo;
	}

	public double getHorasDeConsumo() {
		return horasDeConsumo;
	}

	public double consumoUltimas(int horas) {
		return this.consumoPorHoraAproximada() * horas;
	}

	public double consumoPorHoraAproximada() {
		return this.getHorasDeConsumo() * this.getKwXHora() / 24;
	}

	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return this.consumoPorHoraAproximada() * Hours.hoursBetween(fechaInicial, fechaFinal).getHours();
	}


	/*
	 * public double consumo() { return this.KwXHora * this.horasDeUso; }
	 * 
	 * public Boolean esInteligente() { return false; }
	 * 
	 * public DispositivoAdaptado adaptarAInteligente(String nombreGenerico, Boolean
	 * estado) { return new DispositivoAdaptado(nombreGenerico, this.KwXHora,
	 * estado); }
	 */

}
