package tp0.modelo.reportes;

import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.hogar.zona.Transformador;

@Entity
public class ReporteConsumoTransformador extends ReporteConsumo {
	
	protected long id_transformador;
	@Transient
	Transformador transformador;
	
	public ReporteConsumoTransformador(Transformador transformador, String fechaInicial) {
		super(fechaInicial);
		this.id_transformador = transformador.getId();
		this.transformador = transformador;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void finalizarReporte(DateTime fechaFinal) {
		this.setFechaFin(fechaFinal.toString());
		this.setConsumo(transformador.energiaSuministrada(new DateTime(fechaInicio), fechaFinal));
	}
}