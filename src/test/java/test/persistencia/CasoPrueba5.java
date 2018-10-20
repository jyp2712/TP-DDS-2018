package test.persistencia;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import test.dispositivo.HeladeraMock;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.reportes.ReporteConsumoDispositivo;
import tp0.modelo.reportes.ReporteConsumoTransformador;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class CasoPrueba5{

	/*
	 Dado un hogar y un período, mostrar por consola (interfaz de comandos) el
	consumo total. Dado un dispositivo y un período, mostrar por consola su
	consumo promedio. Dado un transformador y un período, mostrar su consumo
	promedio. Recuperar un dispositivo asociado a un hogar de ese transformador
	e incrementar un 1000 % el consumo para ese período. Persistir el dispositivo.
	Nuevamente mostrar el consumo para ese transformador. 
	 */
	DispositivoInteligente dispositivoInteligente1;
	DispositivoEstandar dispositivoEstandar1;
	List<DispositivoInteligente> dispositivos ;
	List<DispositivoInteligente> dispositivosInteligentesNico;
	List<DispositivoEstandar> dispositivosEstandaresNico;
	HeladeraMock heladeraMock;
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos;
	Cliente nico;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	Transformador transformador1, transformador2;
	EntityManager entityManager;
	EntityTransaction transaction;
	List<Cliente> clientes;
	List<Transformador> transformadores;
	String fechaInicial = "2018-09-01";
	String fechaFinal = "2018-09-30";
		
	@Before
	public void setUp() throws Exception {
		
		heladeraMock = new HeladeraMock();
		
		dispositivos = new ArrayList<>();
		
		dispositivosInteligentesNico = new ArrayList<>();
		
		dispositivosEstandaresNico = new ArrayList<>();
		
		repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
		repositorioDeDispositivos.agregar(Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
		dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivosEstandaresNico.add(dispositivoEstandar1);
		
		dispositivosInteligentesNico.add(dispositivoInteligente1);
		
		
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);

		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);

		categorias = new RepositorioEnMemoria<Categoria>();
		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				dispositivosEstandaresNico , dispositivosInteligentesNico, 0) ;
		nico.setRepositorioCategorias(categorias);
		nico.obtenerCategoria();

		transformador1 = new Transformador();
		transformador2 = new Transformador();
		transformador1.agregarCliente(nico);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		transaction.begin();
			entityManager.persist(dispositivoEstandar1.getDispositivoConcreto());
			entityManager.persist(dispositivoEstandar1);
			entityManager.persist(dispositivoInteligente1.getDispositivoConcreto());
			entityManager.persist(dispositivoInteligente1);
			entityManager.persist(categoria1);
			entityManager.persist(categoria2);
			entityManager.persist(nico);
			entityManager.persist(transformador1);
		transaction.commit();
	}
	/*
	@After
	   public void tearDown() throws Exception {
	      transaction.rollback();
	   }*/
	

	@Test
	public void persistenciaConsumoNico(){
		
		ReporteConsumoCliente reporte = new ReporteConsumoCliente();
		reporte.setFechaInicio(fechaInicial);
		reporte.setFechaFin(fechaFinal);
		reporte.setDNI(nico.getDocumento());
		reporte.setApellidoCliente(nico.getApellido());
		reporte.setNombreCliente(nico.getNombre());
		reporte.setConsumo(20);
		
		transaction.begin();
		entityManager.persist(reporte);
		transaction.commit();	
	};
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNicoMostrarConsumo() {
		List<ReporteConsumoCliente>	reportes = entityManager.createQuery("from ReporteConsumoCliente").getResultList();
		
		ReporteConsumoCliente reporte = reportes.get(0);
		
		//assertTrue(reporte.getConsumo() == 20);
		
		System.out.println(reporte.getConsumo());
	}
	
	@Test
	public void persistenciaDispositivoInteligente1() {
		
		ReporteConsumoDispositivo reporte = new ReporteConsumoDispositivo();
		reporte.setFechaInicio(fechaInicial);
		reporte.setFechaFin(fechaFinal);
		reporte.setNombreDispositivo(dispositivoInteligente1.getNombreGenerico());
		reporte.setConsumo(25);
		
		transaction.begin();
		entityManager.persist(reporte);
		transaction.commit();
		
		
	};
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDispositivoMostrarConsumo() {
		List<ReporteConsumoDispositivo> reportes = entityManager.createQuery("from ReporteConsumoDispositivo").getResultList();
		
		ReporteConsumoDispositivo reporte = reportes.get(0);
		
		//assertTrue(reporte.getConsumo() == 25);
		
		System.out.println(reporte.getConsumo());
	}
	
	@Test
	public void persistenciaTransformador() {
		
		ReporteConsumoTransformador reporte = new ReporteConsumoTransformador();
		reporte.setFechaInicio(fechaInicial);
		reporte.setFechaFin(fechaFinal);
		reporte.setId(transformador1.getId());
		reporte.setConsumo(30);
		
		transaction.begin();
		entityManager.persist(reporte);
		transaction.commit();
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTransformadorMostrarConsumo() {
		
		List<ReporteConsumoTransformador> reportes = entityManager.createQuery("from ReporteConsumoTransformador").getResultList();
		
		ReporteConsumoTransformador reporte = reportes.get(0);

		//assertTrue(reporte.getConsumo() == 30);
		
		System.out.println(reporte.getConsumo());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCambioConsumoDispositivoYMuestroConsumoTransformador() {
	
		List<Transformador> transformadores = entityManager.createQuery("from Transformador").getResultList();
		
		Transformador transformador = transformadores.get(0);
		
		DispositivoInteligente dispositivoTransformador = (DispositivoInteligente) transformador.getClientes().get(0).getDispositivosInteligentes().get(0);
		
		List<ReporteConsumoDispositivo> reportes = entityManager.createQuery("from ReporteConsumoDispositivo reporteConsumo where reporteConsumo.nombreDispositivo = 'HELADERA_CONFREEZER' ").getResultList();
		
		ReporteConsumoDispositivo reporte = reportes.get(0);
		
		int consumoAumentado = (int) (reporte.getConsumo() *100);
		
		reporte.setConsumo(consumoAumentado);
		
		transaction.begin();
		
			entityManager.persist(reporte);
			entityManager.persist(dispositivoTransformador.getDispositivoConcreto());
			entityManager.persist(dispositivoTransformador);
			
		transaction.commit();
		
		System.out.print(transformador.energiaSuministrada(new DateTime(fechaInicial), new DateTime(fechaFinal)));
				
	}

}
	


