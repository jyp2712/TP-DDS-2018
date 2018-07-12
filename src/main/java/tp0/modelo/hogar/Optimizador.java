package tp0.modelo.hogar;

import java.util.List;

import tp0.modelo.dispositivo.Dispositivo;

public interface Optimizador {
	
	public void setCondiciones(List<Dispositivo> dispositivos);
	public double[] optimizar(); 
}
