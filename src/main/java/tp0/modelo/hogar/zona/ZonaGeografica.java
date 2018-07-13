package tp0.modelo.hogar.zona;

import java.util.List;

import org.joda.time.DateTime;

public class ZonaGeografica {
	
	protected List<Transformador> transformadores;
	
	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return transformadores.stream().mapToDouble(transf -> transf.energiaSuministrada(fechaInicial, fechaFinal)).sum();
	}
}
