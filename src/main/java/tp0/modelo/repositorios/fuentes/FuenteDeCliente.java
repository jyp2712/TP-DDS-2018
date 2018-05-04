package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.Cliente;
import tp0.modelo.DecodificadorJson;

public class FuenteDeCliente extends Fuente{

	public FuenteDeCliente(String nombreDeArchivo) {
		super(nombreDeArchivo);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> cargar() {
		DecodificadorJson decodificador = new DecodificadorJson(archivo, Cliente.class);		
		return (List<Cliente>) decodificador.leer();
	}
	
}
