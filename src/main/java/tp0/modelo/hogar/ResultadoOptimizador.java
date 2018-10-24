package tp0.modelo.hogar;

import tp0.modelo.dispositivo.Dispositivo;

public class ResultadoOptimizador {
	
	protected Dispositivo dispositivo;
	protected double resultado;
	
	public ResultadoOptimizador(Dispositivo dispositivo, double resultado) {
		super();
		this.dispositivo = dispositivo;
		this.resultado = resultado;
	}
	
	public Dispositivo getDispositivo() {
		return dispositivo;
	}
	public double getResultado() {
		return resultado;
	}

}
