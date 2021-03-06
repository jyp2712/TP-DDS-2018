package tp0.modelo.reportes;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.hogar.zona.Transformador;

@Entity
public class ReporteConsumoTransformador extends Reporte {
	
	protected long id_transformador;
	protected double consumo;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	public ReporteConsumoTransformador() {};
	
	public double getConsumo() {
		return this.consumo;
	}
	
	public ReporteConsumoTransformador(Transformador transformador, String fechaInicial, String fechaFinal) {
		this.id_transformador = transformador.getId();
		this.setFechaInicio(fechaInicial);
		this.setFechaFin(fechaFinal);
		this.consumo = transformador.energiaSuministrada(new DateTime(fechaInicial), new DateTime(fechaFinal));
	}
	
	public void setConsumo(int consumo) {
		this.consumo = consumo;		
	}
}