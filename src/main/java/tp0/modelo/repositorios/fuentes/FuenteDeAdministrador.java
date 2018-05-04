package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.Administrador;
import tp0.modelo.DecodificadorJson;

public class FuenteDeAdministrador extends Fuente{

	public FuenteDeAdministrador(String nombreDeArchivo) {
		super(nombreDeArchivo);
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> cargar() {
		DecodificadorJson decodificador = new DecodificadorJson(archivo, Administrador.class);		
		return (List<Administrador>) decodificador.leer();
	}
}
