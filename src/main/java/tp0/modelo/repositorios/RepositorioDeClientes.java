package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeCliente;
import tp0.modelo.Cliente;

//SM: En este caso no agrego comportamiento simplemente establezco el tipo.
//La SUPER clase es abstracta asi que debo hacer esto o que deje de ser abstracta...
//En este caso lo dejo porque me da mas expresividad al momento de instanciar.
public class RepositorioDeClientes extends RepositorioEnMemoria<Cliente> {
	/*
	 * public List<Cliente> clientes;
	 * 
	 * public RepositorioDeClientes(FuenteDeCliente fuente) { clientes = new
	 * ArrayList<>(fuente.cargar()); }
	 */
}
