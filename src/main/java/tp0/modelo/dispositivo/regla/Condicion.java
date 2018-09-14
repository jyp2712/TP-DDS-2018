package tp0.modelo.dispositivo.regla;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.joda.time.DateTime;

@Embeddable
public abstract class Condicion{

	@Embedded
	protected SensorAdapter sensor;

	public Condicion() {};
	
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
