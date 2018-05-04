package tp0.modelo.repositorios;

import tp0.modelo.repositorios.fuentes.FuenteDeAdministrador;
import tp0.modelo.repositorios.fuentes.FuenteDeCliente;

public class Repositorios {
	
	private static RepositorioDeClientes repositorioDeClientes;
	private static RepositorioDeAdministradores repositorioDeAdministradores;
	
	public static RepositorioDeClientes obtenerRepositorioDeClientes() {
		if(repositorioDeClientes == null) {
			final String ARCHIVO_DE_CLIENTES = "clientes.json";
			FuenteDeCliente fuenteDeCliente = new FuenteDeCliente(ARCHIVO_DE_CLIENTES);
			repositorioDeClientes = new RepositorioDeClientes(fuenteDeCliente);
		}
		return repositorioDeClientes;
	}
	
	public static RepositorioDeAdministradores obtenerRepositorioDeAdministradores() {
		if(repositorioDeAdministradores == null) {
			final String ARCHIVO_DE_ADMINISTRADORES = "administradores.json";
			FuenteDeAdministrador fuenteDeAdministrador = new FuenteDeAdministrador(ARCHIVO_DE_ADMINISTRADORES);
			repositorioDeAdministradores = new RepositorioDeAdministradores(fuenteDeAdministrador);
		}
		return repositorioDeAdministradores;
	}
}
