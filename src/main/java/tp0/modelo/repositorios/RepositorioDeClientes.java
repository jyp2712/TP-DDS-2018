package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

import tp0.modelo.repositorios.fuentes.FuenteDeCliente;
import tp0.modelo.Cliente;

public class RepositorioDeClientes {

	public List<Cliente> clientes;

	public RepositorioDeClientes(FuenteDeCliente fuente) {
		clientes = new ArrayList<>(fuente.cargar());
	}

	public List<Cliente> todos() {

		return clientes;
	}
}
