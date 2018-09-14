package test.persistencia;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.joda.time.DateTime;
import org.junit.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import test.dispositivo.HeladeraMock;
import test.dispositivo.LavarropasMock;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.Intervalo;
import tp0.modelo.dispositivo.estado.*;
import tp0.modelo.reportes.ReporteEncendidoDispositivo;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class CasoPrueba2 {

	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	String fechaInicial = "2018-09-01";
	String fechaFinal = "2018-09-30";
	EntityManager entityManager;
	EntityTransaction transaction;

	@Before
	public void setUp() throws Exception {
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
				new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
				new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		dispositivoInteligente2.setEstado(Estado.APAGADO);
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);
		dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);

		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void intervalosEncendidoDispositivo() {
		transaction.begin();
		
		repositorioDeDispositivos.todos().forEach(disp -> entityManager.persist(disp));
		
		entityManager.persist(dispositivoInteligente1);
		entityManager.persist(dispositivoInteligente2);
		
		transaction.commit();
		
		List<Intervalo> intervalosLavarropas = dispositivoInteligente2.intervalosEncendido(new DateTime(fechaInicial), new DateTime(fechaFinal));
		
		for(Intervalo intervalo : intervalosLavarropas) {
			ReporteEncendidoDispositivo reporte = new ReporteEncendidoDispositivo();
			reporte.setFechaInicio(intervalo.getFechaInicial());
			reporte.setFechaFin(intervalo.getFechaFinal());
			reporte.setDispositivo(dispositivoInteligente2);
			transaction.begin();
			entityManager.persist(reporte);
			transaction.commit();
		}
		
		List<ReporteEncendidoDispositivo> reportes = entityManager.createQuery("from ReporteEncendidoDispositivo").getResultList();
		
		
		for(ReporteEncendidoDispositivo report : reportes) {
			System.out.println(report.getFechaInicio() + " hasta " + report.getFechaFin());
		}
		
		Assert.assertTrue(reportes.get(0).getFechaInicio().equals("2018-09-01"));
		Assert.assertTrue(reportes.get(0).getFechaFin().equals("2018-09-02"));
		Assert.assertTrue(reportes.get(1).getFechaInicio().equals("2018-09-05"));
		Assert.assertTrue(reportes.get(1).getFechaFin().equals("2018-09-20"));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void modificarNombreDispositivo() {
		transaction.begin();
		repositorioDeDispositivos.todos().forEach(disp -> entityManager.persist(disp));
		transaction.commit();
		
		List<DispositivoConcreto> dispositivos = entityManager.createQuery("from DispositivoConcreto").getResultList();
		
		DispositivoConcreto dispositivo = dispositivos.get(0);
		
		dispositivo.setNombre("HELADERA_SINFREEZER");
		transaction.begin();
		entityManager.flush();
		transaction.commit();
				
		List<DispositivoConcreto> dispositivos_aux = entityManager.createQuery("from DispositivoConcreto").getResultList();

		DispositivoConcreto dispositivo_aux = dispositivos_aux.get(0);
		
		Assert.assertTrue(dispositivo_aux.getNombreGenerico().equals("HELADERA_SINFREEZER"));
	}	

}