package tp0.modelo;

import javax.persistence.Entity;

@Entity
public class Categoria extends PersistentObject{

	protected String nombre;
	protected double cargoFijo;
	protected double cargoVariable;
	protected double consumoMinimo;
	protected double consumoMaximo;

	public Categoria() {}
	
	public Categoria(String id, double cargoFijo, double cargoVariable, double consumoMinimo, double consumoMaximo) {
		setNombre(id);
		setCargoFijo(cargoFijo);
		setCargoVariable(cargoVariable);
		setConsumoMaximo(consumoMaximo);
		setConsumoMinimo(consumoMinimo);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCargoFijo() {
		return cargoFijo;
	}

	private void setCargoFijo(double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public double getCargoVariable() {
		return cargoVariable;
	}

	private void setCargoVariable(double cargoVariable) {
		this.cargoVariable = cargoVariable;
	}

	public double getConsumoMinimo() {
		return consumoMinimo;
	}

	private void setConsumoMinimo(double consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}

	public double getConsumoMaximo() {
		return consumoMaximo;
	}

	private void setConsumoMaximo(double consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	public boolean enRango(Double consumo) {
		return consumo >= getConsumoMinimo() && consumo < getConsumoMaximo();
	}
}