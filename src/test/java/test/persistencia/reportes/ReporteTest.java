
package test.persistencia.reportes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import test.dispositivo.HeladeraMock;
import test.dispositivo.LavarropasMock;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.reportes.ReporteConsumo;
import tp0.modelo.reportes.ReporteConsumoDispositivo;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ReporteTest{

	Cliente nico;
	EntityManager entityManager;
	EntityTransaction transaction;
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoEstandar dispositivoEstandar1;
	DispositivoEstandar dispositivoEstandar2;
	List<ReporteConsumo> reportes;
	ReporteConsumo reporteDispositivoInteligente2;
	ReporteConsumo reporteDispositivoInteligente2Persistido;
	ReporteConsumoDispositivo reporteDispositivoEstandar1;
	ReporteConsumoDispositivo reporteDispositivoEstandar1Persistido;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	List<DispositivoEstandar> dispositivosEstandares = new ArrayList<DispositivoEstandar>();
	List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<DispositivoInteligente>();
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	

	@Before
	public void setUp() throws Exception {
		
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		dispositivoInteligente2.setEstado(Estado.APAGADO);
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);
		dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
		dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);
		dispositivoEstandar2 = new DispositivoEstandar("VENTILADOR_PIE", 24, 2);
		dispositivoEstandar2.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivosEstandares.addAll(Arrays.asList(dispositivoEstandar1, dispositivoEstandar2));
		dispositivosInteligentes.addAll(Arrays.asList(dispositivoInteligente2));

		nico = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				null, dispositivosInteligentes, 0);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
	}
	

	@Test
	public void persistoReporteConUnDispositivoInteligente(){
		
		dispositivoInteligente2.encenderse();
		dispositivoInteligente2.apagarse();
		
		reporteDispositivoInteligente2 = dispositivoInteligente2.getReporteConsumo();
		
		transaction.begin();
		
		entityManager.persist(dispositivoInteligente2.getDispositivoConcreto());
		
		entityManager.persist(dispositivoInteligente2);
		
		entityManager.persist(dispositivoInteligente2.getReporteConsumo());
		
		transaction.commit();
		
		reportes = entityManager.createQuery("from ReporteConsumo").getResultList();
		
		reporteDispositivoInteligente2Persistido = reportes.get(0);
		
		assertTrue(reporteDispositivoInteligente2Persistido.getClass() == reporteDispositivoInteligente2.getClass());
	};

/*	
	@Test
	public void persistoReporteConUnDispositivoEstandar(){
		
		dispositivoEstandar1.finalizarReporte(DateTime.now());
		
		reporteDispositivoEstandar1 = dispositivoEstandar1.getReporte();
		
		transaction.begin();
		
		entityManager.persist(dispositivoEstandar1.getDispositivoConcreto());
		
		entityManager.persist(dispositivoEstandar1);
		
		entityManager.persist(dispositivoEstandar1.getReporte());
		
		transaction.commit();	
		
		reportes = entityManager.createQuery("from Reporte").getResultList();
		
		reporteDispositivoEstandar1Persistido = reportes.get(0);
		
		assertTrue(reporteDispositivoEstandar1Persistido.getClass() == reporteDispositivoEstandar1.getClass());
	};*/

}
