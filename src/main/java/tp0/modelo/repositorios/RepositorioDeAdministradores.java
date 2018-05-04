package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeAdministrador;
import tp0.modelo.Administrador;

//SM: En este caso no agrego comportamiento simplemente establezco el tipo.
//La SUPER clase es abstracta asi que debo hacer esto o que deje de ser abstracta...
//En este caso lo dejo porque me da mas expresividad al momento de instanciar.
public class RepositorioDeAdministradores extends RepositorioEnMemoria<Administrador> {
	/*
	 * public List<Administrador> administradores;
	 * 
	 * public RepositorioDeAdministradores(FuenteDeAdministrador fuente) {
	 * administradores = new ArrayList<>(fuente.cargar()); }
	 */
}
