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
	
	HeladeraMock heladeraMock;
	LavarropasMock lavarropasMock;
	SensorAdapter sensorMock1;
	SensorAdapter sensorMock2;
	Condicion condicionMock1;
	Condicion condicionMock2;
	
	List<Regla> reglas ;
	
	List<DispositivoInteligente> dispositivos ;

	Accion accionMock1;
	Accion accionMock2;
		
	Regla regla1;
	Regla regla1Persistida;
	Regla regla1Persistida2daVez;

	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos;
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	
	@Before
	public void setUp() throws Exception {
		
		heladeraMock = new HeladeraMock();
		lavarropasMock = new LavarropasMock();
		sensorMock1 = new SensorMock();
		sensorMock2 = new SensorMock();
		condicionMock1 = new CondicionMock(sensorMock1);
		condicionMock2 = new CondicionMock(sensorMock2);
		reglas = new ArrayList();
		dispositivos = new ArrayList();
		repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
		
		
		
		repositorioDeDispositivos.agregar(Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
														new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
														new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
														new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		accionMock1 = new AccionMock(dispositivoInteligente1);
		
		regla1 = new Regla(condicionMock1, accionMock1);
		
		regla1.setAccion(accionMock1);
		regla1.setCondicion(condicionMock1);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		
		transaction = entityManager.getTransaction();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCasoPrueba3() {
		
		transaction.begin();
		
		entityManager.persist(dispositivoInteligente1.getDispositivoConcreto());
		
		entityManager.persist(dispositivoInteligente1);
		
		entityManager.persist(regla1.getAccion());
		
		entityManager.persist(regla1.getCondicion());
				
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
		
		assertTrue(regla1Persistida2daVez.getCondicion() == condicionMock2);
		
	}
	
}




