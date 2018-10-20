package tp0.modelo.reportes;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tp0.modelo.dispositivo.DispositivoInteligente;

@Entity
public class ReporteEncendidoDispositivo extends Reporte {
	
	@ManyToOne
	protected DispositivoInteligente dispositivo;

	public ReporteEncendidoDispositivo() {}
	
	public ReporteEncendidoDispositivo(DispositivoInteligente dispositivo, String fechaInicial, String fechaFinal) {
		this.dispositivo = dispositivo;
		this.setFechaInicio(fechaInicial);
		this.setFechaFin(fechaFinal);
	}
	
	public void setDispositivo(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
}