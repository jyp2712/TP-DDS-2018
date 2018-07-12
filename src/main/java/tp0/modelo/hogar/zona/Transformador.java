package tp0.modelo.hogar.zona;

import java.util.List;

import org.joda.time.DateTime;

import tp0.modelo.Cliente;

public class Transformador {
	
	protected List<Cliente> clientes;
	
	public double energiaSuministrada() {
		return clientes.stream().mapToDouble(cliente -> cliente.consumoTotal(DateTime.now(), DateTime.now())).sum();
	}

}
