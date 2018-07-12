package tp0.modelo.dispositivo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoEstandar implements Dispositivo {
	private double usoOptimo;
	@JsonProperty
	protected TipoDispositivoEnum tipoDispositivo;
	@JsonProperty
	protected double horasDeConsumo;

	@JsonCreator
	public DispositivoEstandar(@JsonProperty("nombre generico") TipoDispositivoEnum tipoDispositivo,
			@JsonProperty("Horas de consumo") double horasDeConsumo) {
		setTipoDispositivoEnum(tipoDispositivo);
		setHorasDeConsumo(horasDeConsumo);
	}

	private void setTipoDispositivoEnum(TipoDispositivoEnum nombreGenerico) {
		this.tipoDispositivo = nombreGenerico;
	}

	private void setHorasDeConsumo(double horasDeConsumo) {
		this.horasDeConsumo = horasDeConsumo;
	}

	public double getHorasDeConsumo() {
		return horasDeConsumo;
	}

	public double getkWXHora() {
		return tipoDispositivo.kwPorHora();
	}

	public TipoDispositivoEnum getTipoDispositivoEnum() {
		return tipoDispositivo;
	}

	public double consumoUltimas(int horas) {
		return this.consumoPorHoraAproximada() * horas;
	}

	public double consumoPorHoraAproximada() {
		return this.getHorasDeConsumo() * this.getkWXHora() / 24;
	}

	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return this.consumoPorHoraAproximada() * Hours.hoursBetween(fechaInicial, fechaFinal).getHours();
	}
	public void setUsoOptimo(double horas) {
		usoOptimo=horas;
	}
	public double getUsoOptimo() {
		return usoOptimo;
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
