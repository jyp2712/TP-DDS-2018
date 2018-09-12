package test.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Parameter;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class CasoPrueba1{

	Cliente nico, nicolas;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	Transformador transformador1, transformador2, transf1, transf2;
	EntityManager entityManager;
	EntityTransaction transaction;
	List<Cliente> clientes, clientes_aux;
	List<Transformador> transformadores, transf_aux;
		
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
	public void persistenciaNico(){
		transaction.begin();
		
		entityManager.persist(categoria1);
		entityManager.persist(categoria2);

		entityManager.persist(nico);

		entityManager.persist(transformador1);
		entityManager.persist(transformador2);

		transaction.commit();	
		
		clientes = entityManager.createQuery("from Cliente").getResultList();
		transformadores = entityManager.createQuery("from Transformador").getResultList();
		
		nicolas = clientes.get(0);
		
		assertTrue(transformador1.pertenece(nicolas));
		assertFalse(transformador2.pertenece(nicolas));
	};
	
	@Test
	public void testNicoCambioZona() {
		clientes = entityManager.createQuery("from Cliente").getResultList();
		transformadores = entityManager.createQuery("from Transformador").getResultList();
		
		nicolas = clientes.get(0);
		transf1 = transformadores.get(0);
		transf2 = transformadores.get(1);

		transf1.sacarCliente(nicolas);
		transf2.agregarCliente(nicolas);
		
		transaction.begin();		
		entityManager.flush();
		transaction.commit();
		
		clientes_aux = entityManager.createQuery("from Cliente").getResultList();
		transf_aux = entityManager.createQuery("from Transformador").getResultList();
		
		Cliente cliente_aux = clientes.get(0);
		Transformador transf_aux1 = transformadores.get(0);
		Transformador transf_aux2 = transformadores.get(1);
		
		assertFalse(transf_aux1.pertenece(cliente_aux));
		assertTrue(transf_aux2.pertenece(cliente_aux));
		
	}

}
