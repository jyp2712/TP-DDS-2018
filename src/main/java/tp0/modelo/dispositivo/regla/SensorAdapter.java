package tp0.modelo.dispositivo.regla;

import org.joda.time.DateTime;

public interface SensorAdapter {
	public abstract double medicion(DateTime fechaInicial, DateTime fechaFinal);
}
