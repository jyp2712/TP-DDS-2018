package tp0.modelo.repositorios;

import tp0.modelo.repositorios.fuentes.FuenteJsonDelAdministrador;
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

	private static RepositorioDeAdministradores repositorioDeAdministradores;
	
	public static RepositorioDeAdministradores obtenerRepositorioDeAdministradores() {
		if(repositorioDeAdministradores == null) {
			final String ARCHIVO_DE_ADMINISTRADORES = "administradores.json";
			FuenteJsonDelAdministrador fuenteDeAdministrador = new FuenteJsonDelAdministrador(ARCHIVO_DE_ADMINISTRADORES);
			repositorioDeAdministradores = new RepositorioDeAdministradores(fuenteDeAdministrador);
		}
		return repositorioDeAdministradores;
	}
	
	public static void establecerRepositorioAdministradores(RepositorioDeAdministradores repositorio) {
		repositorioDeAdministradores = repositorio;
	}
	}
