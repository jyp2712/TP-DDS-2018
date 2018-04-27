package tp0.modelo;

import org.joda.time.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Administrador {

	@JsonProperty
	protected Integer id;

	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;

	@JsonProperty
	protected String domicilio;

	@JsonProperty
	protected DateTime fechaAltaSistema;

	@JsonCreator
	public Administrador(@JsonProperty("id") Integer id, @JsonProperty("nombre") String nombre,
			@JsonProperty("apellido") String apellido, @JsonProperty("domicilio") String domicilio,
			@JsonProperty("fecha de alta en el sistema") String fechaAltaSistema) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.fechaAltaSistema = new DateTime(fechaAltaSistema);
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public DateTime getFechaAltaSistema() {
		return fechaAltaSistema;
	}

	public Months antiguedadAdministrador() {
		return Months.monthsBetween(fechaAltaSistema, DateTime.now());
		//return Years.yearsBetween(fechaAltaSistema, DateTime.now()).getYears();
	}
}
