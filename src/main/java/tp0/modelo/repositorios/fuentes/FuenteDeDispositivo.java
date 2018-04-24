package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.Dispositivo;

public interface FuenteDeDispositivo {

	public List<Dispositivo> cargar();
	
	public void guardar(List<Dispositivo> dispositivos);
}
