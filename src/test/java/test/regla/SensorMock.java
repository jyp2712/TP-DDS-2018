package test.regla;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.regla.SensorAdapter;

public class SensorMock extends SensorAdapter {

	@Override
	public double medicion(DateTime fechaInicial, DateTime fechaFinal) {
		return 0;
	}
}
