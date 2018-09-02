package tp0.modelo.hogar;

import java.util.List;

import tp0.modelo.dispositivo.Dispositivo;

public interface Optimizador {
	
	public double[] optimizar(List<Dispositivo> dispositivos); 
}
