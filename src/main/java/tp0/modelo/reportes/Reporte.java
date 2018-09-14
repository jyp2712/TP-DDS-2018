package tp0.modelo.reportes;

import javax.persistence.*;

import tp0.modelo.dispositivo.Intervalo;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reporte{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected long id;

	@Embedded
	protected Intervalo fecha = new Intervalo();
	
	public void setFechaInicio(String fechaInicio) {
		this.fecha.setFechaInicial(fechaInicio);
	}
	
	public void setFechaFin(String fechaFin) {
		this.fecha.setFechaFinal(fechaFin);
	}
		
	public String getFechaInicio() {
		return this.fecha.getFechaInicial();
	}
	
	public String getFechaFin() {
		return this.fecha.getFechaFinal();
	}
	
}