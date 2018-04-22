package tp0.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {
	
	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;
	
	protected Categoria categoria;
	
	@JsonCreator
	public Cliente(
			@JsonProperty("nombre") String nombre,
			@JsonProperty("apellido") String apellido
			) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public String obtenerNombre() {
		return nombre;
	}

	public String obtenerApellido() {
		return apellido;
	}
	
	public double Consumo() {
		return 100;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
