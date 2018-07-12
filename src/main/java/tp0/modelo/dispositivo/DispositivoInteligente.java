package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.dispositivo.regla.Accion;
import tp0.modelo.dispositivo.regla.SensorAdapter;

public class DispositivoInteligente extends Dispositivo {

	private Estado estado;
	private DispositivoFisicoAdapter dispositivoFisico;
	protected SensorAdapter sensor;

	@JsonCreator
	public DispositivoInteligente(@JsonProperty("nombre generico") String nombreGenerico,
			@JsonProperty("KW/H") double KwXHora) {
		this.nombreGenerico = nombreGenerico;
		setKwXHora(KwXHora);
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
	
	public void setSensorAdapter(SensorAdapter sensor) {
		this.sensor = sensor;
	}
	
	public SensorAdapter getSensor() {
		return this.sensor;
	}
	
}
