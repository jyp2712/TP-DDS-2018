package tp0.modelo.reportes;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.Dispositivo;

@Entity
public class ReporteConsumoDispositivo extends Reporte {
	
	protected String nombreDispositivo;
	protected double consumo;
	
	public ReporteConsumoDispositivo() {};
	
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	
	public String getNombreDispositivo() {
		return this.nombreDispositivo;
	}
	
	public double getConsumo() {
		return this.consumo;
	}

	public ReporteConsumoDispositivo(Dispositivo dispositivo, String fechaInicial, String fechaFinal) {
		this.nombreDispositivo = dispositivo.getNombreGenerico();
		this.setFechaInicio(fechaInicial);
		this.setFechaFin(fechaFinal);
		this.consumo = dispositivo.consumoTotal(new DateTime(fechaInicial), new DateTime(fechaFinal));
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;		
	}
	
}