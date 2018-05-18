package tp0.modelo.dispositivo;

import org.joda.time.Hours;
import org.joda.time.DateTime;

public interface DispositivoFisicoAdapter {
	public double consumo(Hours horas);
	
	public double consumoTotal(DateTime periodo);
	
	public void apagar();
	
	public void encender();
	
	public void ahorrarEnergia();
}
