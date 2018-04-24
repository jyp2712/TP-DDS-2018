package tp0.modelo;

import java.util.List;

import tp0.modelo.repositorios.Repositorios;

public class App {
	
	List<Cliente> clientes = Repositorios.obtenerRepositorioDeClientes().todos();
	List<Dispositivo> dispositivos = Repositorios.obtenerRepositorioDeDispositivos().todos();
	
	public static void main(String[] args) {
		new App().start();
	}

	private void start() {
		Dispositivo dispositivo = dispositivos.get(0);
		System.out.println(dispositivo.getNombreGenerico());
		System.out.println(dispositivo.getKwXHora());
		Repositorios.obtenerRepositorioDeDispositivos().remover(dispositivo);
	}

}
