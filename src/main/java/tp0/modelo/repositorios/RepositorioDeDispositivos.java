package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeDispositivo;
import tp0.modelo.repositorios.fuentes.FuenteJsonDelDispositivo;
import tp0.modelo.Dispositivo;

public class RepositorioDeDispositivos {

	private FuenteDeDispositivo fuente;

	public List<Dispositivo> dispositivos;

	public RepositorioDeDispositivos(FuenteDeDispositivo fuente) {
		this.fuente = fuente;
		dispositivos = new ArrayList<>(fuente.cargar());
	}
	
	public RepositorioDeDispositivos(String nombreDelArchivo) {
		List<Dispositivo> dispositivos = new FuenteJsonDelDispositivo(nombreDelArchivo).cargar();
		this.dispositivos.addAll(dispositivos);
	}

	public List<Dispositivo> todos() {

		return dispositivos;
	}

	public void agregar(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);
		guardar();
	}

	public void remover(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);
		guardar();
	}

	private void guardar() {
		fuente.guardar(dispositivos);
	}

}
