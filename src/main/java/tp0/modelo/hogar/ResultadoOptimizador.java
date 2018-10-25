package tp0.modelo.hogar;

import tp0.modelo.dispositivo.Dispositivo;

public class ResultadoOptimizador {
	
	protected Dispositivo dispositivo;
	protected double resultado;
	protected boolean superoConsumo;
	
	public ResultadoOptimizador(Dispositivo dispositivo, double resultado) {
		this.dispositivo = dispositivo;
		this.resultado = resultado;
		this.superoConsumo = dispositivo.getUsoMaximo() <= resultado;
	}
	
	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	public double getResultado() {
		return resultado;
	}
	
	public boolean isSuperoConsumo() {
		return superoConsumo;
	}

}
