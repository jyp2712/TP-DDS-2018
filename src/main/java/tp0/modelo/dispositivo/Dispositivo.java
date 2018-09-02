package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import tp0.modelo.repositorios.RepositorioEnMemoria;


public abstract class Dispositivo {
	
	protected String nombreGenerico;
	protected DispositivoConcreto dispositivoConcreto;
	protected double kWXHora;

	public abstract double consumoUltimas(int horas);
	public abstract double consumoTotal(DateTime fechaInicial, DateTime fechaFinal);
	
	private DispositivoConcreto getDispositivoConcreto() {
		return this.dispositivoConcreto;
	}

	public void setDispositivoGenerico(RepositorioEnMemoria<DispositivoConcreto> dispositivos) {
		this.dispositivoConcreto = dispositivos.encontrar(disp -> this.getNombreGenerico().equals(disp.getNombreGenerico().toString()));
	}

	public double getKwXHora() {
		return this.kWXHora;
	}

	public void setKwXHora(double kwXHora) {
		this.kWXHora = kwXHora;
	}
	
	public double getCoeficiente() {
		return this.getDispositivoConcreto().getCoeficiente();
	}

	public double getUsoMinimo() {
		return this.getDispositivoConcreto().getUsoMinimo();
	}

	public double getUsoMaximo() {
		return this.getDispositivoConcreto().getUsoMaximo();
	}

	public String getNombreGenerico() {
		return this.nombreGenerico;
	}
	
	public boolean optimizable() {
		return this.getDispositivoConcreto().optimizable;
	}
}
