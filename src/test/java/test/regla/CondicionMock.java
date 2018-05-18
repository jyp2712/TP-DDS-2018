package test.regla;

import tp0.modelo.dispositivo.condicion.Condicion;
import tp0.modelo.dispositivo.condicion.SensorAdapter;

public class CondicionMock extends Condicion {

	public CondicionMock(SensorAdapter sensor) {
		super(sensor);
	}

	@Override
	public Boolean cumplida() {
		return false;
	}
}
