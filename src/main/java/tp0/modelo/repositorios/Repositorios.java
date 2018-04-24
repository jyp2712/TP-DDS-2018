package tp0.modelo.repositorios;

import tp0.modelo.repositorios.fuentes.FuenteJsonDelCliente;
import tp0.modelo.repositorios.fuentes.FuenteJsonDelDispositivo;

public class Repositorios {
	
	private static RepositorioDeClientes repositorioDeClientes;
	
	public static RepositorioDeClientes obtenerRepositorioDeClientes() {
		if(repositorioDeClientes == null) {
			final String ARCHIVO_DE_CLIENTES = "clientes.json";
			FuenteJsonDelCliente fuenteDeCliente = new FuenteJsonDelCliente(ARCHIVO_DE_CLIENTES);
			repositorioDeClientes = new RepositorioDeClientes(fuenteDeCliente);
		}
		return repositorioDeClientes;
	}
	
	public static void establecerRepositorioDeClientes(RepositorioDeClientes repositorio) {
		repositorioDeClientes = repositorio;
	}

	private static RepositorioDeDispositivos repositorioDeDispositivos;
	
	public static RepositorioDeDispositivos obtenerRepositorioDeDispositivos() {
		if(repositorioDeDispositivos == null) {
			final String ARCHIVO_DE_DISPOSITIVOS = "dispositivos.json";
			FuenteJsonDelDispositivo fuenteDeDispositivo = new FuenteJsonDelDispositivo(ARCHIVO_DE_DISPOSITIVOS);
			repositorioDeDispositivos = new RepositorioDeDispositivos(fuenteDeDispositivo);
		}
		return repositorioDeDispositivos;
	}
	
	public static void establecerRepositorioDeDispositivos(RepositorioDeDispositivos repositorio) {
		repositorioDeDispositivos = repositorio;
	}
}
