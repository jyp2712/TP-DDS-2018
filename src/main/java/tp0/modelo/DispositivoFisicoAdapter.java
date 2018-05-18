package tp0.modelo;

import org.joda.time.Hours;
import org.joda.time.DateTime;

public interface DispositivoFisicoAdapter {

	public double consumo(Hours horas);
	
	public double consumoTotal(DateTime periodo);
	
}
