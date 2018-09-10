package tp0.modelo.dispositivo.regla;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import tp0.modelo.PersistentObject;

@Entity
public abstract class Condicion extends PersistentObject{

	@ManyToOne
	protected SensorAdapter sensor;

	public Condicion(SensorAdapter sensor) {
		this.setSensor(sensor);
	}

	public SensorAdapter getSensor() {
		return sensor;
	}

	private void setSensor(SensorAdapter sensor) {
		this.sensor = sensor;
	}

	public double medicion(DateTime fechaInicial, DateTime fechaFinal) {
		return this.getSensor().medicion(fechaInicial, fechaFinal);
	}

	public abstract boolean cumplida(double resultado);
}
