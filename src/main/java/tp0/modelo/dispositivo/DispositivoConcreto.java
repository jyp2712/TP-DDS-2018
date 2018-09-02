package tp0.modelo.dispositivo;

public class DispositivoConcreto {
	
	protected String nombreGenerico;
	protected double coeficiente;
	protected double usoMinimo;
	protected double usoMaximo;
	protected boolean optimizable;
	
	public DispositivoConcreto(String nombreGenerico, double coeficiente, double usoMinimo,
			double usoMaximo, boolean optimizable) {
		this.nombreGenerico = nombreGenerico;
		this.coeficiente = coeficiente;
		this.usoMinimo = usoMinimo;
		this.usoMaximo = usoMaximo;
		this.optimizable = optimizable;
	}
	
	public String getNombreGenerico(){
		return this.nombreGenerico;
	}

	public double getCoeficiente() {
		return this.coeficiente;
	}

	public double getUsoMinimo() {
		return this.usoMinimo;
	}

	public double getUsoMaximo() {
		return this.usoMaximo;
	}

	public boolean optimizable() {
		return this.optimizable;
	}
}
