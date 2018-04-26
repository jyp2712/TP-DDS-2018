package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Categoria {

	@JsonProperty
	protected double cargoFijo;

	@JsonProperty
	protected double cargoVariable;

	@JsonProperty
	protected double consumoMinimo;

	@JsonProperty
	protected double consumoMaximo;

	public double getConsumoMinimo() {
		return consumoMinimo;
	}

	public double getConsumoMaximo() {
		return consumoMaximo;
	}

	/*
	 * Constructor privado que usa Jackson para deserializar la formula. Es
	 * necesario para que pueda crear el arbol de sintaxis (Expression) a partir de
	 * la formula, ya que como Expression es una interfaz funcional, no se puede
	 * guardar directamente en el archivo JSON.
	 */
	@JsonCreator
	private Categoria(@JsonProperty("cargo fijo") double cargoFijo,
			@JsonProperty("cargo variable") double cargoVariable,
			@JsonProperty("consumo minimo") double consumoMinimo,
			@JsonProperty("consumo maximo") double consumoMaximo) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.consumoMinimo = consumoMinimo;
		this.consumoMaximo = consumoMaximo;
	}

}
