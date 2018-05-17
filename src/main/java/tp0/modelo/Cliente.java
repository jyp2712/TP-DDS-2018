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
	private String nombreCategoria;

	@JsonProperty
	protected List<DispositivoEstandar> dispositivos;

	@JsonCreator
	public Cliente(@JsonProperty("nombre") String nombre, @JsonProperty("apellido") String apellido,
			@JsonProperty("tipo documento") String tipoDoc, @JsonProperty("N documento") Integer documento,
			@JsonProperty("telefono") String tel, @JsonProperty("domicilio de servicio") String domicilioServicio,
			@JsonProperty("fecha de alta en el servicio") String fechaAltaServicio,
			@JsonProperty("categoria") String nombreCategoria,
			@JsonProperty("dispositivos") List<DispositivoEstandar> dispositivos) {
		setNombre(nombre);
		setApellido(apellido);
		setTipoDoc(DTD.valueOf(tipoDoc));
		setDocumento(documento);
		setTel(tel);
		setDomicilioServicio(domicilioServicio);
		this.nombreCategoria = nombreCategoria;
		setFechaAltaServicio(new DateTime(fechaAltaServicio));
		setDispositivos(dispositivos);
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

	public DTD getTipoDoc() {
		return tipoDoc;
	}

	private void setTipoDoc(DTD tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Integer getDocumento() {
		return documento;
	}

	private void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getTel() {
		return tel;
	}

	private void setTel(String tel) {
		this.tel = tel;
	}

	public String getDomicilioServicio() {
		return domicilioServicio;
	}

	private void setDomicilioServicio(String domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
	}

	public DateTime getFechaAltaServicio() {
		return new DateTime(fechaAltaServicio);
	}

	private void setFechaAltaServicio(DateTime fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	private Repositorio<Categoria> getRepositorioCategorias() {
		return repositorioCategorias;
	}

	public void setRepositorioCategorias(Repositorio<Categoria> repositorioCategorias) {
		this.repositorioCategorias = repositorioCategorias;
	}

	public void obtenerCategoria() {
		this.categoria = repositorioCategorias.encontrar(categoria -> categoria.getNombre().equals(this.nombreCategoria));
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DispositivoEstandar> getDispositivos() {
		return dispositivos;
	}

	private void setDispositivos(List<DispositivoEstandar> dispositivos) {
		this.dispositivos = dispositivos;
	}

	private List<DispositivoEstandar> dispositivosEncendidos() {
		return this.getDispositivos().stream().filter(dispositivo -> dispositivo.estaEncendido())
				.collect(Collectors.toList());
	}

	private List<DispositivoEstandar> dispositivosApagados() {
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
