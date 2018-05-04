package tp0;

import java.util.List;

import tp0.modelo.Administrador;
import tp0.modelo.Cliente;
import tp0.modelo.repositorios.Repositorios;

//TODO: Esto hay que eliminarlo. Hay que hacerlo en todo caso en el tests correspondiente
public class App {

	List<Cliente> clientes = Repositorios.obtenerRepositorioDeClientes().todos();
	List<Administrador> administradores = Repositorios.obtenerRepositorioDeAdministradores().todos();

	public static void main(String[] args) {
		new App().start();
	}

	private void start() {
		Cliente cliente1 = clientes.get(0);
		System.out.println(cliente1.getFechaAltaServicio().toString());
		Repositorios.obtenerRepositorioDeClientes().remover(cliente1);
		Cliente cliente2 = clientes.get(0);
		System.out.println(cliente2.getFechaAltaServicio().toString());
	}

}
