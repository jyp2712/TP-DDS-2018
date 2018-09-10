package tp0.modelo;

import javax.persistence.Entity;

import org.joda.time.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Administrador extends PersistentObject{

	@JsonProperty
	protected Integer id_admin;

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
		setId(id);
		setNombre(nombre);
		setApellido(apellido);
		setDomicilio(domicilio);
		setFechaAltaSistema(new DateTime(fechaAltaSistema));
	}

	public Integer getId() {
		return id_admin;
	}

	private void setId(Integer id) {
		this.id_admin = id;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	private void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public DateTime getFechaAltaSistema() {
		return fechaAltaSistema;
	}

	private void setFechaAltaSistema(DateTime fechaAltaSistema) {
		this.fechaAltaSistema = fechaAltaSistema;
	}

	public Months antiguedadAdministrador() {
		return Months.monthsBetween(fechaAltaSistema, DateTime.now());
	}
}
