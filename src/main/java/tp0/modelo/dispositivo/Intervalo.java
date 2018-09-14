package tp0.modelo.dispositivo;

import javax.persistence.Embeddable;

@Embeddable
public class Intervalo {

	String fechaInicial;
	String fechaFinal;
	
	public Intervalo() {}
	
	public Intervalo(String fechaInicio, String fechaFin) {
		this.fechaInicial = fechaInicio;
		this.fechaFinal = fechaFin;
	}
	
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
}
