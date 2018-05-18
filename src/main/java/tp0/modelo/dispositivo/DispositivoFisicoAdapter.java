package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

public interface DispositivoFisicoAdapter {
	public double consumo(int horas);
	
	public double consumoTotal(DateTime periodo);
	
	public void apagar();
	
	public void encender();
	
	public void ahorrarEnergia();
}
