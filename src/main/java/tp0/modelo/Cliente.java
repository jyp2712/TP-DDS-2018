package tp0.modelo;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {
	
	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;
	
	@JsonProperty
	protected String tipoDoc;

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
			@JsonProperty("dispositivos") List<Dispositivo> dispositivos		
			) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDoc = tipoDoc;
		this.documento = documento;
		this.tel = tel;
		this.domicilioServicio = domicilioServicio;
		this.fechaAltaServicio = new DateTime(fechaAltaServicio);
		this.categoria = Categorizador.vincularCategoria(categoria);
		this.dispositivos = dispositivos;
	}
	

	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getTipoDoc() {
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
	
	public boolean dispositivoEncendido(Dispositivo dispositivo) {
		return dispositivo.estaEncendido();
	}
	
	public long cantidadDispositivosEncendidos() {
		return this.getDispositivos().stream().filter(dispositivo -> dispositivo.estaEncendido()).count();
	}
	
	public long cantidadDispositivosApagados() {
		return this.getDispositivos().stream().filter(dispositivo -> !dispositivo.estaEncendido()).count();
	}
	
	public long cantidadDispositivosTotal() {
		return this.getDispositivos().stream().count();
	}
}
