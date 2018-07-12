package tp0.modelo.dispositivo.regla;

import org.joda.time.DateTime;

public abstract class Condicion {

	private SensorAdapter sensor;

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
