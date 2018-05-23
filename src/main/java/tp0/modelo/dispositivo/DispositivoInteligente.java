package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.dispositivo.regla.Accion;

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

	public double consumoUltimas(int horas) {
		return this.getDispositivoFisico().consumoUltimas(horas);
	}

	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return this.getDispositivoFisico().consumoTotal(fechaInicial, fechaFinal);
	}
	
	public void ejecutar(Accion accion) {
		accion.ejecutar();
	}
}
