package tp0.modelo.hogar.zona;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import tp0.modelo.PersistentObject;

@Entity
public class ZonaGeografica extends PersistentObject{
	
	@OneToMany
	@JoinColumn(name="zonaGeo_id")
	protected List<Transformador> transformadores;
	
	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return transformadores.stream().mapToDouble(transf -> transf.energiaSuministrada(fechaInicial, fechaFinal)).sum();
	}
}
