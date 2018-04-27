package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;

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

	public Months antiguedadAdministrador() {
		return Months.monthsBetween(fechaAltaSistema, DateTime.now());
		//return Years.yearsBetween(fechaAltaSistema, DateTime.now()).getYears();
	}
}
