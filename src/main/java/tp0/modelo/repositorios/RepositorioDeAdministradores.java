package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeAdministrador;
import tp0.modelo.repositorios.fuentes.FuenteJsonDelAdministrador;
import tp0.modelo.Administrador;

public class RepositorioDeAdministradores {

	private FuenteDeAdministrador fuente;

	public List<Administrador> administradores;

	public RepositorioDeAdministradores(FuenteDeAdministrador fuente) {
		this.fuente = fuente;
		administradores = new ArrayList<>(fuente.cargar());
	}
	
	public RepositorioDeAdministradores(String nombreDelArchivo) {
		List<Administrador> administradores = new FuenteJsonDelAdministrador(nombreDelArchivo).cargar();
		this.administradores.addAll(administradores);
	}

	public List<Administrador> todos() {

		return administradores;
	}

	public void agregar(Administrador administrador) {
		administradores.add(administrador);
		guardar();
	}

	public void remover(Administrador administrador) {
		administradores.remove(administrador);
		guardar();
	}

	private void guardar() {
		fuente.guardar(administradores);
	}

}
