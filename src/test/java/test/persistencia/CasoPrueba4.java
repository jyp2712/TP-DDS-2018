package test.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Cliente;
import tp0.modelo.hogar.zona.Transformador;
import tp0.modelo.json.DecodificadorJson;
import tp0.modelo.repositorios.fuentes.FuenteArchivo;

public class CasoPrueba4 {

	DecodificadorJson decodificador = new DecodificadorJson(new FuenteArchivo("transformadores.json"), Transformador.class);
	List<Transformador> transformadores = new ArrayList<>();
	Transformador transformador1, transformador2;
	Cliente nico;
	EntityManager entityManager;
	EntityTransaction transaction;
	int cantidadTransformadores;
	
	@Before
	public void setUp() throws Exception {
		
		nico = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				null, null, 0);
		transformador1 = new Transformador();
		transformador2 = new Transformador();
		transformador1.agregarCliente(nico);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		transaction.begin();

		entityManager.persist(nico);

		entityManager.persist(transformador1);
		entityManager.persist(transformador2);

		transaction.commit();	

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPersistenciaTransformadores() {
		transformadores = entityManager.createQuery("from Transformador").getResultList();
		Assert.assertEquals(2, transformadores.size(), 0);

		transformadores.addAll((Collection<? extends Transformador>) decodificador.leer());

		Assert.assertEquals(3, transformadores.size(), 0);

		transaction.begin();
		transformadores.forEach(transf -> entityManager.persist(transf));
		transaction.commit();
		
		List<Transformador> transformadores_aux = entityManager.createQuery("from Transformador").getResultList();
		Assert.assertEquals(3, transformadores_aux.size(), 0);
	
	}
}
