package tp0.modelo;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.repositorios.Repositorio;

public class Cliente {

	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;

	private enum DTD {
		LE, DNI, CI, LC
	}

	@JsonProperty
	protected DTD tipoDoc;

	@JsonProperty
	protected Integer documento;

	@JsonProperty
	protected String tel;

	@JsonProperty
	protected String domicilioServicio;

	@JsonProperty
	protected DateTime fechaAltaServicio;

	protected Repositorio<Categoria> repositorioCategorias;

	@JsonProperty
	protected Categoria categoria;

	@JsonProperty
	protected List<Dispositivo> dispositivos;

	@JsonCreator
	public Cliente(@JsonProperty("nombre") String nombre, @JsonProperty("apellido") String apellido,
			@JsonProperty("tipo documento") String tipoDoc, @JsonProperty("N documento") Integer documento,
			@JsonProperty("telefono") String tel, @JsonProperty("domicilio de servicio") String domicilioServicio,
			@JsonProperty("fecha de alta en el servicio") String fechaAltaServicio,
			Repositorio<Categoria> repositorioCategorias,
			// SM: Como estan las cosas conviene que no tenga categoria en el json y tenga
			// por defecto la menor
			@JsonProperty("dispositivos") List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDoc = DTD.valueOf(tipoDoc);
		this.documento = documento;
		this.tel = tel;
		this.domicilioServicio = domicilioServicio;
		this.fechaAltaServicio = new DateTime(fechaAltaServicio);
		// SM: Asigno la categoria segun el consumo inicial. Acordarlo con el cliente!
		this.asignarCategoria();
		this.dispositivos = dispositivos;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public DTD getTipoDoc() {
		return tipoDoc;
	}

	public Integer getDocumento() {
		return documento;
	}

	public String getTel() {
		return tel;
	}

	public String getDomicilioServicio() {
		return domicilioServicio;
	}

	public DateTime getFechaAltaServicio() {
		return new DateTime(fechaAltaServicio);
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	private List<Dispositivo> dispositivosEncendidos() {
		return this.getDispositivos().stream().filter(dispositivo -> dispositivo.estaEncendido())
				.collect(Collectors.toList());
	}

	private List<Dispositivo> dispositivosApagados() {
		return this.getDispositivos().stream().filter(dispositivo -> !dispositivo.estaEncendido())
				.collect(Collectors.toList());
	}

	public boolean tieneAlgunDispositivoEncendido() {
		return !this.dispositivosEncendidos().isEmpty();
	}

	public long cantidadDispositivosEncendidos() {
		return this.dispositivosEncendidos().size();
	}

	public long cantidadDispositivosApagados() {
		return this.dispositivosApagados().size();
	}

	public long cantidadDispositivosTotal() {
		return this.getDispositivos().stream().count();
	}

	public double consumoEstimadoTotal() {
		return this.dispositivosEncendidos().stream().mapToDouble(dispositivo -> dispositivo.getKwXHora()).sum();
	}

	public void asignarCategoria() {
		this.setCategoria(
				// SM: Si la lambda resulta poco expresiva se puede hacer un predicado. Para mi,
				// esta bien...
				this.repositorioCategorias.encontrar(categoria -> categoria.enRango(this.consumoEstimadoTotal())));
	}
}
