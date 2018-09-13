package tp0.modelo.reportes;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ReporteConsumo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected long id;
	protected String fechaInicio;
	protected String fechaFin;
	protected double consumo;
	
	
	public abstract void finalizarReporte(String fechaFinal); 
	
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin= fechaFin;
	}
	
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}
	
	public String getFechaInicio() {
		return this.fechaInicio;
	}
	
	public String getFechaFin() {
		return this.fechaFin;
	}
	
	public double getConsumo() {
		return this.consumo;
	}
	
	
}