package tp0.modelo.reportes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import tp0.modelo.PersistentObject;
import tp0.modelo.dispositivo.Dispositivo;

@Entity
public class Reporte extends PersistentObject{

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dispositivo_id")
	protected Dispositivo dispositivo;
	
	protected String fechaInicioPeriodo;
	
	protected String fechaFinPeriodo;
	
	protected double consumoPeriodo;
	
	public Reporte(Dispositivo dispositivo) {
		
		setDispositivo(dispositivo);
		
	}
	
	private void setDispositivo(Dispositivo dispositivo) {
		
		this.dispositivo = dispositivo;
		
	}
	
	public void setFechaFinPeriodo(String fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}
	
	public void setFechaInicioPeriodo(String fechaInicioPeriodo) {
		
		this.fechaInicioPeriodo = fechaInicioPeriodo;
		
	}
	
	public void setConsumoPeriodo(double consumo) {
		
		this.consumoPeriodo = consumo;
		
	}
	
	public Dispositivo getDispositivo() {
		
		return this.dispositivo;
		
	}
	
	public String getFechaInicioPeriodo() {
		
		return this.fechaInicioPeriodo;
		
	}
	
	public String getFechaFinPeriodo() {
		
		return this.fechaFinPeriodo;
		
	}
	
	public double getConsumoPeriodo() {
		
		return this.consumoPeriodo;
		
	}
}
