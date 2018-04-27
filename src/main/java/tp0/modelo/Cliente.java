package tp0.modelo;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {

	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;

	private enum DTD {LE,DNI,CI,LC}
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

	@JsonProperty
	protected Categoria categoria;

	@JsonProperty
	protected List<Dispositivo> dispositivos;

	@JsonCreator
	public Cliente(
			@JsonProperty("nombre") String nombre, 
			@JsonProperty("apellido") String apellido,
			@JsonProperty("tipo documento") String tipoDoc, 
			@JsonProperty("N documento") Integer documento,
			@JsonProperty("telefono") String tel, 
			@JsonProperty("domicilio de servicio") String domicilioServicio,
			@JsonProperty("fecha de alta en el servicio") String fechaAltaServicio,
			@JsonProperty("categoria") String categoria, 
			@JsonProperty("dispositivos") List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDoc = DTD.valueOf(tipoDoc);
		this.documento = documento;
		this.tel = tel;
		this.domicilioServicio = domicilioServicio;
		this.fechaAltaServicio = new DateTime(fechaAltaServicio);
		this.categoria = Categorizador.getCategorizador().getCategoria(categoria);
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
		this.setCategoria(Categorizador.getCategorizador().determinarCategoria(this.consumoEstimadoTotal())); 
	}
}
