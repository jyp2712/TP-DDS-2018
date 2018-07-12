package tp0.modelo.dispositivo;

public class DispositivoConcreto {
	
	protected DispositivoConcretoEnum nombreGenerico;
	protected double coeficiente;
	protected double usoMinimo;
	protected double usoMaximo;
	
	public DispositivoConcreto(DispositivoConcretoEnum nombreGenerico, double coeficiente, double usoMinimo,
			double usoMaximo) {
		this.nombreGenerico = nombreGenerico;
		this.coeficiente = coeficiente;
		this.usoMinimo = usoMinimo;
		this.usoMaximo = usoMaximo;
	}
	
	public DispositivoConcretoEnum getNombreGenerico(){
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

}
