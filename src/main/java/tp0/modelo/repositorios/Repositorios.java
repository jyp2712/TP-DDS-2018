package tp0.modelo.repositorios;

import tp0.modelo.repositorios.fuentes.FuenteJsonDelCliente;

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
}
