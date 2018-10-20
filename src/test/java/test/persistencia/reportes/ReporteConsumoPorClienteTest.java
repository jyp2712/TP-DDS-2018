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

import tp0.modelo.Cliente;
import tp0.modelo.reportes.ReporteConsumoCliente;


public class ReporteConsumoPorClienteTest{
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	List<ReporteConsumoCliente> reportesConsumo;
	
	Cliente nico, lucas;
	
	ReporteConsumoCliente reporteCliente1;
	ReporteConsumoCliente reporteCliente2;
	ReporteConsumoCliente reporteCliente3;
	ReporteConsumoCliente reporteCliente4;
	
	@Before
	public void setUp() throws Exception {
	
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		nico = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				null, null, 0) ;
		
		lucas = new Cliente("Lucas", "Perez", "DNI", 39068889, "1141693940", "Calle Falsa 124", "2018-01-02", "R1",
				null, null, 0) ;
		
		reporteCliente1 = new ReporteConsumoCliente();
		reporteCliente1.setFechaInicio("2018-01-01");
		reporteCliente1.setFechaFin("2018-01-31");
		reporteCliente1.setDNI(nico.getDocumento());
		reporteCliente1.setApellidoCliente(nico.getApellido());
		reporteCliente1.setNombreCliente(nico.getNombre());
		reporteCliente1.setConsumo(20);
		
		reporteCliente2 = new ReporteConsumoCliente();
		reporteCliente2.setFechaInicio("2018-02-01");
		reporteCliente2.setFechaFin("2018-02-28");
		reporteCliente2.setDNI(nico.getDocumento());
		reporteCliente2.setApellidoCliente(nico.getApellido());
		reporteCliente2.setNombreCliente(nico.getNombre());
		reporteCliente2.setConsumo(20);
		
		reporteCliente3 = new ReporteConsumoCliente();
		reporteCliente3.setFechaInicio("2018-01-01");
		reporteCliente3.setFechaFin("2018-01-31");
		reporteCliente3.setDNI(lucas.getDocumento());
		reporteCliente3.setApellidoCliente(lucas.getApellido());
		reporteCliente3.setNombreCliente(lucas.getNombre());
		reporteCliente3.setConsumo(20);
		
		reporteCliente4 = new ReporteConsumoCliente();
		reporteCliente4.setFechaInicio("2018-02-01");
		reporteCliente4.setFechaFin("2018-02-28");
		reporteCliente4.setDNI(lucas.getDocumento());
		reporteCliente4.setApellidoCliente(lucas.getApellido());
		reporteCliente4.setNombreCliente(lucas.getNombre());
		reporteCliente4.setConsumo(20);
		
		reportesConsumo = new ArrayList<ReporteConsumoCliente>();
		
		reportesConsumo.addAll(Arrays.asList(reporteCliente1, reporteCliente2, reporteCliente3, reporteCliente4));
			
	}	
	
	@After
	   public void tearDown() throws Exception {
	      transaction.rollback();
	}
	
	@SuppressWarnings("unchecked")
	@Test
		public void testReporteConsumoPorCliente() {
		transaction.begin();
		
		reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
		
		Query query = entityManager.createQuery("from ReporteConsumoCliente");
		
		reportesConsumo = (List<ReporteConsumoCliente>) query.getResultList();
		
		assertTrue(reportesConsumo.stream().count() == 4);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
		public void testReportePromedioPorCliente() {
		transaction.begin();
		
		
		reportesConsumo.stream().forEach(disp -> entityManager.persist(disp));
					
		Query query = entityManager.createQuery(	
				"SELECT nombreCliente, apellidoCliente, dni, AVG(consumo) FROM ReporteConsumoCliente"
						+ " GROUP BY nombreCliente, apellidoCliente, dni"
						+ " ORDER BY AVG(consumo) DESC");
		
		reportesConsumo = (List<ReporteConsumoCliente>) query.getResultList();
		
		assertTrue(reportesConsumo.stream().count() == 2);				
	}
		
}