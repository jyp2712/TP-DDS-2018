package tp0.modelo.dispositivo;

import org.joda.time.DateTime;

public interface Dispositivo {
	public double consumoUltimas(int horas);
	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal);
	public TipoDispositivoEnum getTipoDispositivoEnum();
	public void setUsoOptimo(double horas);
}
