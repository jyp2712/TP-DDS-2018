package tp0.modelo.repositorios.fuentes;

import java.io.File;

import tp0.App;

public class Fuente {

	File archivo;

	public Fuente(String nombreDeArchivo) {
		this.archivo = obtenerRecurso(nombreDeArchivo);
	}
	
	public File obtenerRecurso(String nombre) {
		ClassLoader cargadorDeClase = App.class.getClassLoader();
		File archivo;

		try {
			archivo = new File(cargadorDeClase.getResource(nombre).getFile());
		} catch(NullPointerException e) {
			String mensaje = String.format("Error al intentar leer el recurso \"%s\"", nombre);
			throw new RuntimeException(mensaje);
		}

		return archivo;
	}
}
