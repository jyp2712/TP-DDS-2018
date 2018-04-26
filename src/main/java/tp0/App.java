package tp0;

import java.util.List;

import tp0.modelo.Administrador;
import tp0.modelo.Cliente;
import tp0.modelo.Dispositivo;
import tp0.modelo.repositorios.Repositorios;

public class App {
	
	List<Cliente> clientes = Repositorios.obtenerRepositorioDeClientes().todos();
	List<Dispositivo> dispositivos = Repositorios.obtenerRepositorioDeDispositivos().todos();
	List<Administrador> administradores = Repositorios.obtenerRepositorioDeAdministradores().todos();
	
	public static void main(String[] args) {
		new App().start();
	}

	private void start() {
		Cliente cliente1 = clientes.get(0);
		Cliente cliente2 = clientes.get(1);
		System.out.println(cliente1.getFechaAltaServicio().toString());
		System.out.println(cliente2.getFechaAltaServicio().toString());
	}

}
