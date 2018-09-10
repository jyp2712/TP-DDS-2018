package test.hogar;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.regla.SensorAdapter;

public class SensorHogarMock extends SensorAdapter {

	@Override
	public double medicion(DateTime fechaInicial, DateTime fechaFinal) {
		return 40;
	}
}
