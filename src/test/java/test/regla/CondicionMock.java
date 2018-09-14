package test.regla;

import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.dispositivo.regla.SensorAdapter;

public class CondicionMock extends Condicion {
	
	public CondicionMock(SensorAdapter sensor) {
		super(sensor);
	}

	@Override
	public boolean cumplida(double resultado) {
		return true;
	}
}
