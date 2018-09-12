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
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class CasoPrueba1{

	Cliente nico;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	Transformador transformador1, transformador2;
	EntityManager entityManager;
	EntityTransaction transaction;
	
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
	public void testNicoCambioZona() {
		transaction.begin();
		
		entityManager.persist(categoria1);
		entityManager.persist(categoria2);

		entityManager.persist(nico);

		entityManager.persist(transformador1);
		entityManager.persist(transformador2);

		transaction.commit();

		List<Cliente> clientes = entityManager.createQuery("from Cliente").getResultList();
		List<Transformador> transformadores = entityManager.createQuery("from Transformador").getResultList();
		
		Cliente nicolas = clientes.get(0);
		Transformador transf1 = transformadores.get(0);
		Transformador transf2 = transformadores.get(1);

		transf1.sacarCliente(nicolas);
		transf2.agregarCliente(nicolas);
		
		transaction.begin();		
		entityManager.flush();
		transaction.commit();
		
	}

}
