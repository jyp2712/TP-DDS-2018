package tp0.modelo.reportes;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.hogar.zona.Transformador;

@Entity
public class ReporteConsumoTransformador extends ReporteConsumo {
	
	protected long id_transformador;
	@Transient
	Transformador transformador;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void comenzarReporte(Transformador transformador, String fechaInicial) {
		this.id_transformador = transformador.getId();
		this.transformador = transformador;
		this.fechaInicio = fechaInicial;
	}
	
	public void finalizarReporte(String fechaFinal) {
		this.setFechaFin(fechaFinal);
		this.setConsumo(transformador.energiaSuministrada(new DateTime(fechaInicio), new DateTime(fechaFinal)));
	}
}