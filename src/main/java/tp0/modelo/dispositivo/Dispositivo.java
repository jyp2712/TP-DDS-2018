package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

public interface Dispositivo {
	public double consumo(int horas);
	public double consumoTotal(DateTime periodo);
}
