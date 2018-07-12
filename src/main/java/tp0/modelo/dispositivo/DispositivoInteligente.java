package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.dispositivo.estado.EstadoEnum;
import tp0.modelo.dispositivo.regla.Accion;

public class DispositivoInteligente implements Dispositivo {
	private double usoOptimo;

	@JsonProperty
	protected TipoDispositivoEnum tipoDispositivo;

	private Estado estado;
	private EstadoEnum estadoEnum;
	private DispositivoFisicoAdapter dispositivoFisico;

	@JsonCreator
	public DispositivoInteligente(@JsonProperty("nombre generico") TipoDispositivoEnum tipoDispositivo) {
		setTipoDispositivoEnum(tipoDispositivo);
	}

	public TipoDispositivoEnum getNombreGenerico() {
		return tipoDispositivo;
	}
	public TipoDispositivoEnum getTipoDispositivoEnum() {
		return tipoDispositivo;
	}


	private void setTipoDispositivoEnum(TipoDispositivoEnum nombreGenerico) {
		this.tipoDispositivo = nombreGenerico;
	}

	public double getKwXHora() {
		return tipoDispositivo.kwPorHora();
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

	public void setEstadoEnum(EstadoEnum estado) {
		this.estadoEnum = estado;
	}
	
	public EstadoEnum getEstadoEnum() {
		return estadoEnum;
	}
	public void setUsoOptimo(double horas) {
		usoOptimo=horas;
	}
	public double getUsoOptimo() {
		return usoOptimo;
	}
}
