package test.persistencia;

import java.util.Arrays;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.reportes.ReporteConsumoCliente;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class CasoPrueba5{

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
		
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);

		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);

		categorias = new RepositorioEnMemoria<Categoria>();
		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				null, null, 0);
		nico.setRepositorioCategorias(categorias);
		nico.obtenerCategoria();

		transformador1 = new Transformador();
		transformador2 = new Transformador();
		transformador1.agregarCliente(nico);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
	}
	

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
		
		System.out.println(reporte.getConsumo());
	}

}
