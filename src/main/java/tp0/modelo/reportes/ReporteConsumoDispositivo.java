package tp0.modelo.reportes;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.Dispositivo;

@Entity
public class ReporteConsumoDispositivo extends ReporteConsumo {
	
	protected String nombreDispositivo;
	protected double consumo;
	
	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}
	
	public String getNombreDispositivo() {
		return this.nombreDispositivo;
	}

	public ReporteConsumoDispositivo(Dispositivo dispositivo, String fechaInicial, String fechaFinal) {
		this.nombreDispositivo = dispositivo.getNombreGenerico();
		this.fechaInicio = fechaInicial;
		this.fechaFin = fechaFinal;
		this.consumo = dispositivo.consumoTotal(new DateTime(fechaInicial), new DateTime(fechaFinal));
	}

	
	
}