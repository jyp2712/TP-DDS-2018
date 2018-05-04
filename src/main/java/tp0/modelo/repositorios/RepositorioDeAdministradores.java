package tp0.modelo.repositorios;

import tp0.modelo.Administrador;

//SM: En este caso no agrego comportamiento simplemente establezco el tipo.
//La SUPER clase es abstracta asi que debo hacer esto o que deje de ser abstracta...
//En este caso lo dejo porque me da mas expresividad al momento de instanciar.
public class RepositorioDeAdministradores extends RepositorioEnMemoria<Administrador> {
	/*
	 * private FuenteDeAdministrador fuente;
	 * 
	 * public RepositorioDeAdministradores(FuenteDeAdministrador fuente) {
	 * this.fuente = fuente; administradores = new ArrayList<>(fuente.cargar()); }
	 * 
	 * public RepositorioDeAdministradores(String nombreDelArchivo) {
	 * List<Administrador> administradores = new
	 * FuenteJsonDelAdministrador(nombreDelArchivo).cargar();
	 * this.administradores.addAll(administradores); }
	 * 
	 * void guardar() { fuente.guardar(administradores); }
	 */
}