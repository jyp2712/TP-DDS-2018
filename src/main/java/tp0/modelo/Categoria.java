package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Categoria {
	
	@JsonProperty
	protected double cargoFijo; 

	@JsonProperty
	protected double cargoVariable;

	@JsonProperty
	protected double valorMinimo;
	
	@JsonProperty
	protected double valorMaximo;
	
	public double getValorMinimo() {
		return valorMinimo;
	}
	public double getValorMaximo() {
		return valorMaximo;
	}
	
	/*
	 * Constructor privado que usa Jackson para deserializar la fórmula.
	 * Es necesario para que pueda crear el árbol de sintaxis (Expression) a partir de la fórmula,
	 * ya que como Expression es una interfaz funcional, no se puede guardar directamente en el archivo JSON.
	 */
	@JsonCreator
	private Categoria(
			@JsonProperty("cargo fijo") double cargoFijo,
			@JsonProperty("cargo variable") double cargoVariable,
			@JsonProperty("valor minimo") double valorMinimo,
			@JsonProperty("valor maximo") double valorMaximo) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
	}
	
}
