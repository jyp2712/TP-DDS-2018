package tp0.modelo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.repositorios.Repositorio;

public class Cliente {

	protected String nombre;
	protected String apellido;
	private enum DTD {
		LE, DNI, CI, LC
	}
	protected DTD tipoDoc;
	protected Integer documento;
	protected String tel;
	protected Hogar hogarServicio;
	protected DateTime fechaAltaServicio;
	protected Repositorio<Categoria> repositorioCategorias;
	protected Categoria categoria;
	protected String nombreCategoria;
	protected List<DispositivoEstandar> dispositivosEstandares;
	protected List<DispositivoInteligente> dispositivosInteligentes;
	protected double puntos;	

	@JsonCreator
	public Cliente(@JsonProperty("nombre") String nombre, @JsonProperty("apellido") String apellido,
			@JsonProperty("tipo documento") String tipoDoc, @JsonProperty("N documento") Integer documento,
			@JsonProperty("telefono") String tel, @JsonProperty("domicilio de servicio") String domicilioServicio,
			@JsonProperty("fecha de alta en el servicio") String fechaAltaServicio,
			@JsonProperty("categoria") String nombreCategoria,
			@JsonProperty("dispositivos estandar") List<DispositivoEstandar> dispositivosEstandares,
			@JsonProperty("dispositivos inteligentes") List<DispositivoInteligente> dispositivosInteligentes,
			@JsonProperty("puntos") double puntos) {
		setNombre(nombre);
		setApellido(apellido);
		setTipoDoc(DTD.valueOf(tipoDoc));
		setDocumento(documento);
		setTel(tel);
		setNombreCategoria(nombreCategoria);
		setFechaAltaServicio(new DateTime(fechaAltaServicio));
		setDispositivosEstandares(dispositivosEstandares);
		setDispositivosInteligentes(dispositivosInteligentes);
		setDomicilioServicio(domicilioServicio);
		setPuntos(puntos);
	}

	private void setDomicilioServicio(String domicilioServicio) {
		this.hogarServicio = new Hogar(domicilioServicio);
		
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

	public Hogar getDomicilioServicio() {
		return this.hogarServicio;
	}

	public DateTime getFechaAltaServicio() {
		return new DateTime(fechaAltaServicio);
	}

	private void setFechaAltaServicio(DateTime fechaAltaServicio) {
		this.fechaAltaServicio = fechaAltaServicio;
	}

	public void setRepositorioCategorias(Repositorio<Categoria> repositorioCategorias) {
		this.repositorioCategorias = repositorioCategorias;
	}

	public Repositorio<Categoria> getRepositorioCategorias() {
		return repositorioCategorias;
	}

	public void obtenerCategoria() {
		this.categoria = repositorioCategorias
				.encontrar(categoria -> categoria.getNombre().equals(this.nombreCategoria));
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}

	public List<DispositivoEstandar> getDispositivosEstandar() {
		return dispositivosEstandares;
	}

	public void setDispositivosEstandares(List<DispositivoEstandar> dispositivos) {
		this.dispositivosEstandares = dispositivos;
	}

	public void setDispositivosInteligentes(List<DispositivoInteligente> dispositivos) {
		this.dispositivosInteligentes = dispositivos;
	}

	private void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public double getPuntos() {
		return this.puntos;
	}

	public void sumarPuntos(double puntos) {
		this.puntos += puntos;
	}

	private void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	private List<DispositivoInteligente> dispositivosInteligentesEncendidos() {
		return this.getDispositivosInteligentes().stream().filter(dispositivo -> dispositivo.estaEncendido())
				.collect(Collectors.toList());
	}

	private List<DispositivoInteligente> dispositivosInteligentesApagados() {
		return this.getDispositivosInteligentes().stream().filter(dispositivo -> dispositivo.estaApagado())
				.collect(Collectors.toList());
	}

	public boolean tieneAlgunDispositivoEncendido() {
		return !this.dispositivosInteligentesEncendidos().isEmpty();
	}

	public long cantidadDispositivosEncendidos() {
		return this.dispositivosInteligentesEncendidos().size();
	}

	public long cantidadDispositivosApagados() {
		return this.dispositivosInteligentesApagados().size();
	}

	public long cantidadDispositivosTotal() {
		return this.cantidadDispositivosInteligentes() + this.cantidadDispositivosEstandares();
	}

	public long cantidadDispositivosInteligentes() {
		return this.getDispositivosInteligentes().stream().count();
	}

	public long cantidadDispositivosEstandares() {
		return this.getDispositivosEstandar().stream().count();
	}

	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		return Stream.concat(this.getDispositivosInteligentes().stream(), this.getDispositivosEstandar().stream())
				.mapToDouble(dispositivo -> dispositivo.consumoTotal(fechaInicial, fechaFinal)).sum();
	}

	public void asignarCategoria() {
		this.setCategoria(this.repositorioCategorias
				.encontrar(categoria -> categoria.enRango(this.consumoTotal(DateTime.now().minusMonths(3), DateTime.now()))));
	}

	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivo) {
		this.dispositivosInteligentes.add(nuevoDispositivo);
		this.sumarPuntos(15);
	}

	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivo) {
		this.dispositivosEstandares.add(nuevoDispositivo);
	}

	public void convertirDispositivoEstandarAInteligente(DispositivoEstandar dispositivoExistente) {
		DispositivoInteligente nuevoDispositivo = new DispositivoInteligente(dispositivoExistente.getNombreGenerico(),
				dispositivoExistente.getKwXHora());
		this.getDispositivosEstandar().remove(dispositivoExistente);
		this.getDispositivosInteligentes().add(nuevoDispositivo);
		this.sumarPuntos(10);
	}

	public List<Dispositivo> getDispositivos() {
		return  Stream.concat(this.getDispositivosEstandar().stream()
				, this.getDispositivosInteligentes().stream())
				.collect(Collectors.toList());
	}

}
