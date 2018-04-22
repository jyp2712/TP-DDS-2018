package tp0.modelo;

import java.util.List;

import tp0.modelo.repositorios.Repositorios;

public class App {
	
	List<Cliente> clientes = Repositorios.obtenerRepositorioDeClientes().todos();
	
	public static void main(String[] args) {
		new App().start();
	}

	private void start() {
		Cliente cliente = clientes.get(0);
		System.out.println(cliente.obtenerNombre());
		System.out.println(cliente.obtenerApellido());
		Repositorios.obtenerRepositorioDeClientes().remover(cliente);
	}

}
