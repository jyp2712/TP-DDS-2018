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
	
	public ReporteConsumoDispositivo(Dispositivo dispositivo, String fechaInicial) {
		
		super(fechaInicial);
		this.nombreDispositivo = dispositivo.getNombreGenerico();
		this.dispositivo = dispositivo;
		
	}
	
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	
	public String getNombreDispositivo() {
		return this.nombreDispositivo;
	}
	
	public void finalizarReporte(DateTime fechaFinal){
		this.setFechaFin(fechaFinal.toString());
		this.setConsumo(dispositivo.consumoTotal(new DateTime(fechaInicio), fechaFinal));
		
	}
	}