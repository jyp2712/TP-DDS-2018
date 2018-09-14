package tp0.modelo.dispositivo;

import javax.persistence.Entity;

import tp0.modelo.PersistentObject;

@Entity
public class DispositivoConcreto extends PersistentObject{
	
	protected String nombreGenerico;
	protected double coeficiente;
	protected double usoMinimo;
	protected double usoMaximo;
	protected boolean optimizable;
	
	public DispositivoConcreto() {};
	
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

	public void setNombre(String nombre) {
		this.nombreGenerico = nombre;
	}
}
