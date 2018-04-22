package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.CodificadorJson;
import tp0.modelo.Cliente;

public class FuenteJsonDelCliente implements FuenteDeCliente{
	
	CodificadorJson codificador;
	
	public FuenteJsonDelCliente(String nombreDeArchivo){
		codificador = new CodificadorJson(nombreDeArchivo, Cliente.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> cargar() {
		
		return (List<Cliente>) codificador.leer();
	}

	@Override
	public void guardar(List<Cliente> clientes) {
		
		codificador.escribir(clientes);		
	}
	

}
