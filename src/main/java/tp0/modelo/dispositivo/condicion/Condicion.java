package tp0.modelo.dispositivo.condicion;

public class Condicion {
	
	private SensorAdapter sensor;
	
	public Condicion(SensorAdapter sensor) {
		this.setSensor(sensor);
	}

	private SensorAdapter getSensor() {
		return sensor;
	}

	private void setSensor(SensorAdapter sensor) {
		this.sensor = sensor;
	}
	
	public double medicion() {
		return this.getSensor().medicion();
	}
}
