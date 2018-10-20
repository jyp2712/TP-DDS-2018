package test.persistencia.reportes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.reportes.ReporteConsumoDispositivo;

public class ReporteConsumoDispositivoTest{
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	List<DispositivoInteligente> dispositivosInteligentes;
	
	DispositivoEstandar dispositivoEstandar1;
	List<DispositivoEstandar> dispositivosEstandares;
	
	
	ReporteConsumoDispositivo reporteDispositivo1;
	ReporteConsumoDispositivo reporteDispositivo2;
	ReporteConsumoDispositivo reporteDispositivo3;
	ReporteConsumoDispositivo reporteDispositivo4;
	ReporteConsumoDispositivo reporteDispositivo5;
	
	List<ReporteConsumoDispositivo> reportesConsumo;
	
	@Before
	public void setUp() throws Exception {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		dispositivosEstandares = new ArrayList<DispositivoEstandar>();
		dispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		
		
		dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		
		dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
		
		dispositivosEstandares.addAll(Arrays.asList(dispositivoEstandar1));
		dispositivosInteligentes.addAll(Arrays.asList(dispositivoInteligente1, dispositivoInteligente2));
		
		reporteDispositivo1 = new ReporteConsumoDispositivo();
		reporteDispositivo1.setFechaInicio("2018-01-01");
		reporteDispositivo1.setFechaFin("2018-01-31");
		reporteDispositivo1.setNombreDispositivo(dispositivoInteligente1.getNombreGenerico());
		reporteDispositivo1.setConsumo(25);
		
		reporteDispositivo2 = new ReporteConsumoDispositivo();
		reporteDispositivo2.setFechaInicio("2018-02-01");
		reporteDispositivo2.setFechaFin("2018-02-28");
		reporteDispositivo2.setNombreDispositivo(dispositivoInteligente1.getNombreGenerico());
		reporteDispositivo2.setConsumo(25);
		
		reporteDispositivo3 = new ReporteConsumoDispositivo();
		reporteDispositivo3.setFechaInicio("2018-01-01");
		reporteDispositivo3.setFechaFin("2018-01-31");
		reporteDispositivo3.setNombreDispositivo(dispositivoInteligente2.getNombreGenerico());
		reporteDispositivo3.setConsumo(25);
		
		reporteDispositivo4 = new ReporteConsumoDispositivo();
		reporteDispositivo4.setFechaInicio("2018-01-01");
		reporteDispositivo4.setFechaFin("2018-01-28");
		reporteDispositivo4.setNombreDispositivo(dispositivoEstandar1.getNombreGenerico());
		reporteDispositivo4.setConsumo(25);
		
		reporteDispositivo5 = new ReporteConsumoDispositivo();
		reporteDispositivo5.setFechaInicio("2018-02-01");
		reporteDispositivo5.setFechaFin("2018-02-28");
		reporteDispositivo5.setNombreDispositivo(dispositivoEstandar1.getNombreGenerico());
		reporteDispositivo5.setConsumo(25);
		

		
		reportesConsumo = new ArrayList<ReporteConsumoDispositivo>();
		
		reportesConsumo.addAll(Arrays.asList(reporteDispositivo1, reporteDispositivo2, reporteDispositivo3, reporteDispositivo4, reporteDispositivo5));
		
	}
	
	@After
	   public void tearDown() throws Exception {
	      transaction.rollback();
	   }
	
	@SuppressWarnings("unchecked")
	@Test
	public void testReporteConsumoPorDispositivo() {
		
		transaction.begin();
		
		reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
		
		
		Query query = entityManager.createQuery("from ReporteConsumoDispositivo");
		
		reportesConsumo = (List<ReporteConsumoDispositivo>) query.getResultList();
		
		assertTrue(reportesConsumo.stream().count() == 5);				
	}
	
	@SuppressWarnings("unchecked")
	@Test
		public void testReporteConsumoPromedioPorDispositivo() {
		
		transaction.begin();
			
			
			reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
						
			Query query = entityManager.createQuery(	
					"SELECT nombreDispositivo, AVG(consumo) FROM ReporteConsumoDispositivo"
							+ " GROUP BY nombreDispositivo"
							+ " ORDER BY AVG(consumo) DESC");
			
			reportesConsumo = (List<ReporteConsumoDispositivo>) query.getResultList();
			
			assertTrue(reportesConsumo.stream().count() == 3);				
	}
		
}