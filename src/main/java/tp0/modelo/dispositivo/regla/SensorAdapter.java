package tp0.modelo.dispositivo.regla;

import javax.persistence.Embeddable;

import org.joda.time.DateTime;

@Embeddable
public abstract class SensorAdapter{
	public abstract double medicion(DateTime fechaInicial, DateTime fechaFinal);
}
