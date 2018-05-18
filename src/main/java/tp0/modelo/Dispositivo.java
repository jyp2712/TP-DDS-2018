package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

public interface Dispositivo {

	public double consumo(Hours horas);
	public double consumoTotal(DateTime periodo);
	//TODO: Consumo por período
}
