package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.Cliente;

public interface FuenteDeCliente {

	public List<Cliente> cargar();
	
	public void guardar(List<Cliente> clientes);
}
