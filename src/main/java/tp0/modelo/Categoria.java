package tp0.modelo;

public class Categoria {

	private String nombre;
	private double cargoFijo;
	private double cargoVariable;
	private double consumoMinimo;
	private double consumoMaximo;
	
	//TODO: Me da la sensacion como que esta al limite de que son muchos parametros. Por ahora queda. Acepto opiniones
	public Categoria(String id, double cargoFijo, double cargoVariable, double consumoMinimo, double consumoMaximo) {
		this.nombre = id;
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.consumoMaximo = consumoMaximo;
		this.consumoMinimo = consumoMinimo;
	}

	public String getNombre() {
		return nombre;
	}
	
	public double getCargoFijo() {
		return cargoFijo;
	}
	
	public double getCargoVariable() {
		return cargoVariable;
	}
	
	public double getConsumoMinimo() {
		return consumoMinimo;
	}

	public double getConsumoMaximo() {
		return consumoMaximo;
	}
	
	
}