package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.accion.Accion;
import tp0.modelo.dispositivo.estado.Estado;

public class DispositivoInteligente implements Dispositivo {

	@JsonProperty
	protected String nombreGenerico;
	@JsonProperty
	protected double KwXHora;

	private Estado estado;
	private DispositivoFisicoAdapter dispositivoFisico;

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
		return this.getEstado().estaEncendido();
	}

	public Boolean estaApagado() {
		return !this.getEstado().estaEncendido();
	}

	public void apagarse() {
		this.getEstado().apagar(this);
	}

	public void encenderse() {
		this.getEstado().encender(this);
	}

	public void ahorrarseEnergia() {
		this.getEstado().ahorrarEnergia(this);
	}

	public DispositivoFisicoAdapter getDispositivoFisico() {
		return this.dispositivoFisico;
	}

	public void setDispositivoFisico(DispositivoFisicoAdapter dispositivoFisico) {
		this.dispositivoFisico = dispositivoFisico;
	}

	/*
	 * public double consumo() { return this.KwXHora; }
	 */

	/*
	 * public Boolean esInteligente() { return true; }
	 */

	public double consumo(int horas) {
		return this.getDispositivoFisico().consumo(horas);
	}

	public double consumoTotal(DateTime periodo) {
		return this.getDispositivoFisico().consumoTotal(periodo);
		// * Hours.hoursBetween(DateTime.now(), periodo).getHours()
	}
	
	public void ejecutar(Accion accion) {
		accion.ejecutar();
	}

	// public int otorgarPuntos() {
	// return 15;
	// }

}
