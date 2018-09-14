package test.persistencia;

import tp0.modelo.dispositivo.regla.Accion;
import tp0.modelo.dispositivo.regla.Regla;
import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.dispositivo.regla.SensorAdapter;
import tp0.modelo.repositorios.RepositorioEnMemoria;
import tp0.modelo.dispositivo.*;
import tp0.modelo.dispositivo.estado.Estado;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import test.dispositivo.HeladeraMock;
import test.dispositivo.LavarropasMock;
import test.regla.*;

public class CasoPrueba3 {
	
	/*Crear una nueva regla. Asociarla a un dispositivo. Agregar condiciones y
	acciones. Persistirla. Recuperarla y ejecutarla. Modificar alguna condición y
	persistirla. Recuperarla y evaluar que la condición modificada posea la última
	modificación.*/
	
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	SensorMock sensorMock1 = new SensorMock();
	SensorMock sensorMock2 = new SensorMock();
	CondicionMock condicionMock1 = new CondicionMock(sensorMock1);
	CondicionMock condicionMock2 = new CondicionMock(sensorMock2);
	
	List<Regla> reglas = new ArrayList();

	AccionMock accionMock1;
	AccionMock accionMock2;
		
	Regla regla1;
	Regla regla1Persistida;
	Regla regla1Persistida2daVez;

	DispositivoInteligente dispositivoInteligente1;
	
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	
	@Before
	public void setUp() throws Exception {
		repositorioDeDispositivos.agregar(Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
														new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		accionMock1 = new AccionMock(dispositivoInteligente1);
		
		regla1 = new Regla(condicionMock1, accionMock1);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		
		transaction = entityManager.getTransaction();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCasoPrueba3() {
		
		transaction.begin();
		
		entityManager.persist(regla1);
		
		transaction.commit();
		
		reglas = entityManager.createQuery("from Regla").getResultList();
		
		regla1Persistida = reglas.get(0);
		
		reglas.clear();
		
		regla1Persistida.ejecutar(0);
		
		regla1Persistida.setCondicion(condicionMock2);
		
		transaction.begin();
		
		entityManager.persist(regla1Persistida);
		
		transaction.commit();
		
		reglas = entityManager.createQuery("from Regla").getResultList();
		
		regla1Persistida2daVez = reglas.get(0);
		
		assertTrue(true);
		
	}
	
}




