package tp0.modelo.repositorios;

import tp0.modelo.Cliente;

//SM: En este caso no agrego comportamiento simplemente establezco el tipo.
//La SUPER clase es abstracta asi que debo hacer esto o que deje de ser abstracta...
//En este caso lo dejo porque me da mas expresividad al momento de instanciar.
public class RepositorioDeClientes extends RepositorioEnMemoria<Cliente> {
	/*
	 * private FuenteDeCliente fuente;
	 * 
	 * public RepositorioDeClientes(FuenteDeCliente fuente) { this.fuente = fuente;
	 * clientes = new ArrayList<>(fuente.cargar()); }
	 * 
	 * public RepositorioDeClientes(String nombreDelArchivo) { List<Cliente>
	 * clientes = new FuenteJsonDelCliente(nombreDelArchivo).cargar();
	 * this.clientes.addAll(clientes); }
	 */
}
