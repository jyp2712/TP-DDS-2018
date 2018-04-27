package tp0.modelo;

public class Categoria {

	private enum CategoriaId {R1,R2,R3,R4,R5,R6,R7,R8,R9}
	private CategoriaId id;
	private double cargoFijo;
	private double cargoVariable;
	private double consumoMinimo;
	private double consumoMaximo;
	
	//TODO: Me da la sensacion como que esta al limite de que son muchos parametros. Por ahora queda. Acepto opiniones
	public Categoria(String id, double cargoFijo, double cargoVariable, double consumoMinimo, double consumoMaximo) {
		this.id = CategoriaId.valueOf(id);
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.consumoMaximo = consumoMaximo;
		this.consumoMinimo = consumoMinimo;
	}
	

	public double getConsumoMinimo() {
		return consumoMinimo;
	}

	public CategoriaId getId() {
		return id;
	}

	public double getConsumoMaximo() {
		return consumoMaximo;
	}
}