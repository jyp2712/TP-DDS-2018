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


import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.reportes.ReporteConsumoTransformador;

public class ReporteConsumoTransformadorTest{
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	ReporteConsumoTransformador reporteTransformador1;
	ReporteConsumoTransformador reporteTransformador2;
	ReporteConsumoTransformador reporteTransformador3;
	
	List<ReporteConsumoTransformador> reportesConsumo;
	
	Transformador transformador1;
	
	@Before
	public void setUp() throws Exception {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		transformador1 = new Transformador();
		
		reporteTransformador1 = new ReporteConsumoTransformador();
		reporteTransformador1.setFechaInicio("2018-01-01");
		reporteTransformador1.setFechaFin("2018-01-31");
		reporteTransformador1.setId(transformador1.getId());
		reporteTransformador1.setConsumo(30);
		
		reporteTransformador2 = new ReporteConsumoTransformador();
		reporteTransformador2.setFechaInicio("2018-02-01");
		reporteTransformador2.setFechaFin("2018-02-28");
		reporteTransformador2.setId(transformador1.getId());
		reporteTransformador2.setConsumo(30);
		
		reporteTransformador3 = new ReporteConsumoTransformador();
		reporteTransformador3.setFechaInicio("2018-03-01");
		reporteTransformador3.setFechaFin("2018-03-31");
		reporteTransformador3.setId(transformador1.getId());
		reporteTransformador3.setConsumo(30);
		
		reportesConsumo = new ArrayList<ReporteConsumoTransformador>();
		
		reportesConsumo.addAll(Arrays.asList(reporteTransformador1, reporteTransformador2, reporteTransformador3));
		
		
	}
	
	@After
	   public void tearDown() throws Exception {
	      transaction.rollback();
	   }
	
	@SuppressWarnings("unchecked")
	@Test
		public void testReporteConsumoPorTransformador() {
		transaction.begin();
		
		reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
		
		Query query = entityManager.createQuery("from ReporteConsumoTransformador");
		
		reportesConsumo = (List<ReporteConsumoTransformador>) query.getResultList();
		
		assertTrue(reportesConsumo.stream().count() == 3);
				
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testReporteConsumoPromedioPorTransformador() {
	transaction.begin();
	
	reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
	
	Query query = entityManager.createQuery(
			"SELECT id_transformador, AVG(consumo) FROM ReporteConsumoTransformador"
			+ " GROUP BY id_transformador"
			+ " ORDER BY id_transformador DESC");
	
	reportesConsumo = (List<ReporteConsumoTransformador>) query.getResultList();
	
	assertTrue(reportesConsumo.stream().count() == 1);
			
}
	
}	