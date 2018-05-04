package tp0.modelo.repositorios;

import java.util.Arrays;

import tp0.modelo.Categoria;
import tp0.modelo.repositorios.fuentes.FuenteDeAdministrador;
import tp0.modelo.repositorios.fuentes.FuenteDeCliente;

//SM: TODO: Mirar un poco esto. Capaz hay otras maneras de hacerlo. Por ahora lo dejo asi para poder seguir implementando lo que necesito en este momento
//Me parece bastante evidente que una mejora seria aplicar una clase generica, ya que siempre hace lo mismo
public class Repositorios {

	private static RepositorioDeCategorias repositorioDeCategorias;

	// Por el momento dejo las categorias aqui dentro. Luego se pueden asignar de
	// manera mas dinamica o en el test.
	public static RepositorioDeCategorias obtenerRepositorioDeCategorias() {
		// SM: TODO: Tal vez tendriamos que manejar un error???
		if (repositorioDeCategorias == null) {
			repositorioDeCategorias = new RepositorioDeCategorias();
			repositorioDeCategorias.agregar(Arrays.asList(new Categoria("R1", 18.76, 0.644, 0, 150),
					new Categoria("R2", 35.32, 0.644, 150, 325), new Categoria("R3", 60.71, 0.681, 325, 400),
					new Categoria("R4", 71.74, 0.738, 400, 450), new Categoria("R5", 110.38, 0.794, 450, 500),
					new Categoria("R6", 220.75, 0.832, 500, 600), new Categoria("R7", 443.59, 0.851, 600, 700),
					new Categoria("R8", 545.96, 0.851, 700, 1400),
					new Categoria("R9", 887.19, 0.851, 1400, Double.POSITIVE_INFINITY)));
		}
		return repositorioDeCategorias;
	}

	private static RepositorioDeClientes repositorioDeClientes;

	public static RepositorioDeClientes obtenerRepositorioDeClientes() {
		if (repositorioDeClientes == null) {
			final String ARCHIVO_DE_CLIENTES = "clientes.json";
			FuenteDeCliente fuenteDeCliente = new FuenteDeCliente(ARCHIVO_DE_CLIENTES);
			// SM: TODO: Ya se que la manqueo aca pero necesito mas tiempo para ajustar esta
			// parte
			repositorioDeClientes = new RepositorioDeClientes(/* fuenteDeCliente */);
		}
		return repositorioDeClientes;
	}

	public static void establecerRepositorioDeClientes(RepositorioDeClientes repositorio) {
		repositorioDeClientes = repositorio;
	}

	private static RepositorioDeAdministradores repositorioDeAdministradores;

	public static RepositorioDeAdministradores obtenerRepositorioDeAdministradores() {
		if (repositorioDeAdministradores == null) {
			final String ARCHIVO_DE_ADMINISTRADORES = "administradores.json";
			FuenteDeAdministrador fuenteDeAdministrador = new FuenteDeAdministrador(ARCHIVO_DE_ADMINISTRADORES);
			// SM: TODO: Ya se que la manqueo aca pero necesito mas tiempo para ajustar esta
			// parte
			repositorioDeAdministradores = new RepositorioDeAdministradores(/* fuenteDeAdministrador */);
		}
		return repositorioDeAdministradores;
	}

	public static void establecerRepositorioAdministradores(RepositorioDeAdministradores repositorio) {
		repositorioDeAdministradores = repositorio;
	}
}
