package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public class DispositivoEstandar implements Dispositivo {

	protected String nombreGenerico;
	protected double kWXHora;
	protected double horasDeConsumo;

	public DispositivoEstandar(String nombreGenerico, double kWXHora, double horasDeConsumo) {
		setNombreGenerico(nombreGenerico);
		setkWXHora(kWXHora);
		setHorasDeConsumo(horasDeConsumo);
	}

	private void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	private void setkWXHora(double kWXHora) {
		this.kWXHora = kWXHora;
	}

	private void setHorasDeConsumo(double horasDeConsumo) {
		this.horasDeConsumo = horasDeConsumo;
	}

	public double getHorasDeConsumo() {
		return horasDeConsumo;
	}

	public double getkWXHora() {
		return kWXHora;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double consumo(Hours horas) {
		return this.consumoPorHoraAproximada() * horas.getHours();
	}

	private double consumoPorHoraAproximada() {
		return this.getHorasDeConsumo() * this.getkWXHora() / 24;
	}
	
	public double consumoTotal(DateTime periodo) {
		return this.consumoPorHoraAproximada() * Hours.hoursBetween(DateTime.now(), periodo).getHours();
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
