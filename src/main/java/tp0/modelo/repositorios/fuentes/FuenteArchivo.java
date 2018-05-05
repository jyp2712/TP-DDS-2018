package tp0.modelo.repositorios.fuentes;

import tp0.App;
import java.io.File;

public class FuenteArchivo implements Fuente<File>{

	String nombreDeArchivo;

	public FuenteArchivo (String nombreDeArchivo) {
		this.nombreDeArchivo = nombreDeArchivo;
	}
	
	@Override
	public File obtenerRecurso() {
		ClassLoader cargadorDeClase = App.class.getClassLoader();
		File archivo;

		try {
			archivo = new File(cargadorDeClase.getResource(nombreDeArchivo).getFile());
		} catch(NullPointerException e) {
			String mensaje = String.format("Error al intentar leer el recurso \"%s\"", nombreDeArchivo);
			throw new RuntimeException(mensaje);
		}

		return archivo;
	}
	
}
