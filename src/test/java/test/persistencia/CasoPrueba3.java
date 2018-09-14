package test.persistencia;

import tp0.modelo.dispositivo.regla.Accion;

import tp0.modelo.dispositivo.regla.Regla;
import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.repositorios.RepositorioEnMemoria;
import tp0.modelo.dispositivo.*;
import tp0.modelo.dispositivo.estado.Estado;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import test.regla.*;

public class CasoPrueba3 {
	
	/*Crear una nueva regla. Asociarla a un dispositivo. Agregar condiciones y
	acciones. Persistirla. Recuperarla y ejecutarla. Modificar alguna condición y
	persistirla. Recuperarla y evaluar que la condición modificada posea la última
	modificación.*/

	Regla regla;
	Accion accion;
	Condicion condicion;
	DispositivoInteligente dispositivoInteligente;
	
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();;
	
	EntityManager entityManager;
	EntityTransaction transaction;
	
	
	@Before
	public void setUp() throws Exception {
		
		
		
		repositorioDeDispositivos.agregar(Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
														new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
														new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
														new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		dispositivoInteligente = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente.setDispositivoGenerico(repositorioDeDispositivos);
		
		accion = new Accion(dispositivoInteligente);

		condicion = new CondicionMock(null) {
			@Override
			public boolean cumplida(double resultado) {
				return false;
			}

		};
		
		
		regla = new Regla(condicion, accion);
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		
		transaction = entityManager.getTransaction();
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCasoPrueba3() {
		
				
		transaction.begin();
		
		entityManager.persist(dispositivoInteligente.getDispositivoConcreto());
		entityManager.persist(dispositivoInteligente);
		entityManager.persist(accion);
		entityManager.persist(regla);
		
		transaction.commit();
		
		List<Regla> reglas = entityManager.createQuery("from Regla").getResultList();
		
		Regla regla1 = reglas.get(0);		
		
		regla.ejecutar(0);
		
		Condicion condicion2 = new CondicionMock(null) {
			@Override
			public boolean cumplida(double resultado) {
				return true;
			}

		};
		
		regla1.setCondicion(condicion2);
		
		transaction.begin();
		entityManager.flush();
		transaction.commit();
		
		reglas = entityManager.createQuery("from Regla").getResultList();
		
		Regla regla2 = reglas.get(0);
		
		Assert.assertTrue(regla2.getCondicion() == condicion2);
		
	}
	
}