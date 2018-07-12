package tp0.modelo.hogar.zona;

import java.util.List;

public class ZonaGeografica {
	
	protected List<Transformador> transformadores;
	
	public double consumoTotal() {
		return transformadores.stream().mapToDouble(transf -> transf.energiaSuministrada()).sum();
	}
}
