package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeCliente;
import tp0.modelo.repositorios.fuentes.FuenteJsonDelCliente;
import tp0.modelo.Cliente;

public class RepositorioDeClientes {

	private FuenteDeCliente fuente;

	public List<Cliente> clientes;

	public RepositorioDeClientes(FuenteDeCliente fuente) {
		this.fuente = fuente;
		clientes = new ArrayList<>(fuente.cargar());
	}
	
	public RepositorioDeClientes(String nombreDelArchivo) {
		List<Cliente> clientes = new FuenteJsonDelCliente(nombreDelArchivo).cargar();
		this.clientes.addAll(clientes);
	}

	public List<Cliente> todos() {

		return clientes;
	}

	public void agregar(Cliente cliente) {
		clientes.add(cliente);
		guardar();
	}

	public void remover(Cliente cliente) {
		clientes.remove(cliente);
		guardar();
	}

	private void guardar() {
		fuente.guardar(clientes);
	}

}
