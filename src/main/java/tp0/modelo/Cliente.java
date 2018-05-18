package tp0.modelo;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.repositorios.Repositorio;

public class Cliente {

	@JsonProperty
	protected String nombre;

	@JsonProperty
	protected String apellido;

	private enum DTD {
		LE, DNI, CI, LC
	}

	//private ArrayList<ObservadorConversionDeDispositivo> observadoresConversionDeDispositivo;
	
	//private ArrayList<ObservadorRegistroDispositivoInteligente> observadoresRegistroDispositivoInteligente;
	
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
	protected List<DispositivoEstandar> dispositivosEstandares;

	@JsonProperty
	protected List<DispositivoInteligente> dispositivosInteligentes;

	protected long puntos;

	@JsonCreator
	public Cliente(@JsonProperty("nombre") String nombre, @JsonProperty("apellido") String apellido,
			@JsonProperty("tipo documento") String tipoDoc, @JsonProperty("N documento") Integer documento,
			@JsonProperty("telefono") String tel, @JsonProperty("domicilio de servicio") String domicilioServicio,
			@JsonProperty("fecha de alta en el servicio") String fechaAltaServicio,
			@JsonProperty("categoria") String nombreCategoria,
			@JsonProperty("dispositivos estandar") List<DispositivoEstandar> dispositivosEstandares,
			@JsonProperty("dispositivos inteligentes") List<DispositivoInteligente> dispositivosInteligentes,
			long puntos) {
		setNombre(nombre);
		setApellido(apellido);
		setTipoDoc(DTD.valueOf(tipoDoc));
		setDocumento(documento);
		setTel(tel);
		setDomicilioServicio(domicilioServicio);
		setNombreCategoria(nombreCategoria);
		setFechaAltaServicio(new DateTime(fechaAltaServicio));
		setDispositivosEstandares(dispositivosEstandares);
		setDispositivosInteligentes(dispositivosInteligentes);
		setPuntos(puntos);
		//this.observadoresConversionDeDispositivo = new ArrayList<ObservadorConversionDeDispositivo>();
		//this.observadoresRegistroDispositivoInteligente = new ArrayList<ObservadorRegistroDispositivoInteligente>();
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

	public void setRepositorioCategorias(Repositorio<Categoria> repositorioCategorias) {
		this.repositorioCategorias = repositorioCategorias;
	}
	
	public Repositorio<Categoria> getRepositorioCategorias(){
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

	private void setPuntos(long puntos) {
		this.puntos = puntos;
	}
	
	public long getPuntos() {
		return this.puntos;
	}
	
	private void sumarPuntos(long puntos) {
		this.puntos += puntos;
	}

	private void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

//	private void setDispositivos(List<DispositivoInteligente> dispositivosInteligentes) {
//		this.dispositivosInteligentes = dispositivosInteligentes;
//	}

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

	public double consumoTotal(DateTime periodo) {
		return this.consumoTotalDispositivosInteligentes(periodo)
				+ this.consumoTotalEstimadoDispositivosEstandares(periodo);
	}

	public double consumoTotalDispositivosInteligentes(DateTime periodo) {
		return this.getDispositivosInteligentes().stream().mapToDouble(dispositivo -> dispositivo.consumoTotal(periodo))
				.sum();
	}

	public double consumoTotalEstimadoDispositivosEstandares(DateTime periodo) {
		return this.getDispositivosEstandar().stream().mapToDouble(dispositivo -> dispositivo.consumoTotal(periodo))
				.sum();
	}

	public void asignarCategoria() {
		this.setCategoria(
				this.repositorioCategorias
						.encontrar(categoria -> categoria.enRango(this.consumoTotal(DateTime.now().minusMonths(3)))));
	}
	
	public void registrarDispositivoInteligente(DispositivoInteligente nuevoDispositivo) {
		this.dispositivosInteligentes.add(nuevoDispositivo);
		this.sumarPuntos(15);
	}
	
	public void registrarDispositivoEstandar(DispositivoEstandar nuevoDispositivo) {
		this.dispositivosEstandares.add(nuevoDispositivo);
	}
	
	public void convertirDispositivoEstandarAInteligente(DispositivoEstandar dispositivoExistente) {
		DispositivoInteligente nuevoDispositivo = new DispositivoInteligente(dispositivoExistente.getNombreGenerico(), dispositivoExistente.getkWXHora());
		this.getDispositivosEstandar().remove(dispositivoExistente);
		this.registrarDispositivoInteligente(nuevoDispositivo);
		this.sumarPuntos(10);
	}
	//Decidimos no implementar el patrón Observer.
	
	
/*	private void notificarConversionDispositivo() {
		this.observadoresConversionDeDispositivo.stream().forEach(observadorCambio -> observadorCambio.notificar(this));
	}
	
	private void notificarRegistroDispositivo() {
		this.observadoresRegistroDeDispositivo.stream().forEach(observadorRegistro -> observadorRegistro.notificar(this));
	}*/
}
