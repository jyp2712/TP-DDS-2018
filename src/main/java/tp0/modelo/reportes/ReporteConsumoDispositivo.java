package tp0.modelo.reportes;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.Dispositivo;

@Entity
public class ReporteConsumoDispositivo extends ReporteConsumo {
	
	protected String nombreDispositivo;
	@Transient
	protected Dispositivo dispositivo;
	
	
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	
	public String getNombreDispositivo() {
		return this.nombreDispositivo;
	}
	
	public void comenzarReporte(Dispositivo dispositivo, String fechaInicial) {
		this.fechaInicio = fechaInicial;
		this.dispositivo = dispositivo;
	}
	
	public void finalizarReporte(String fechaFinal){
		this.setFechaFin(fechaFinal);
		this.setConsumo(dispositivo.consumoTotal(new DateTime(fechaInicio), new DateTime(fechaFinal)));
		
	}
	}