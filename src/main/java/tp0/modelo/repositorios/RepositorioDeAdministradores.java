package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeAdministrador;
import tp0.modelo.Administrador;

public class RepositorioDeAdministradores {

	public List<Administrador> administradores;

	public RepositorioDeAdministradores(FuenteDeAdministrador fuente) {
		administradores = new ArrayList<>(fuente.cargar());
	}

	public List<Administrador> todos() {

		return administradores;
	}

}
