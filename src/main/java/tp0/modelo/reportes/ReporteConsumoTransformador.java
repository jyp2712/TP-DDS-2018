package tp0.modelo.reportes;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.hogar.zona.Transformador;

@Entity
public class ReporteConsumoTransformador extends ReporteConsumo {
	
	protected long id_transformador;
	protected double consumo;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public ReporteConsumoTransformador(Transformador transformador, String fechaInicial, String fechaFinal) {
		this.id_transformador = transformador.getId();
		this.fechaInicio = fechaInicial;
		this.fechaFin = fechaFinal;
		this.consumo = transformador.energiaSuministrada(new DateTime(fechaInicial), new DateTime(fechaFinal));
	}
}