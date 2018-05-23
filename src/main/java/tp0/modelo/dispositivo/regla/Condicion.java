package tp0.modelo.dispositivo.regla;

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

	public double medicion() {
		return this.getSensor().medicion();
	}

	public abstract Boolean cumplida();
}
