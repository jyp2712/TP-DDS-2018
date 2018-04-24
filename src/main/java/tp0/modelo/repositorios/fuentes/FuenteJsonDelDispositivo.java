package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.CodificadorJson;
import tp0.modelo.Dispositivo;

public class FuenteJsonDelDispositivo implements FuenteDeDispositivo{
	
	CodificadorJson codificador;
	
	public FuenteJsonDelDispositivo(String nombreDeArchivo){
		codificador = new CodificadorJson(nombreDeArchivo, Dispositivo.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dispositivo> cargar() {
		
		return (List<Dispositivo>) codificador.leer();
	}

	@Override
	public void guardar(List<Dispositivo> dispositivos) {
		
		codificador.escribir(dispositivos);		
	}
	

}
