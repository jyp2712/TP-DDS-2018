package tp0.modelo.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import tp0.modelo.dispositivo.DispositivoConcreto;

public class RepositoriosDispositivosGenericos {

	public static final RepositorioEnMemoria<DispositivoConcreto> repositorioDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	static EntityManager entityManager;
	static EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public static void cargarDispositivos() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		repositorioDispositivos.setRepositorio(entityManager.createQuery("from DispositivoConcreto").getResultList());
	}

	public static DispositivoConcreto findDispositivo(String nombre){
		return repositorioDispositivos.encontrar(d -> d.getNombreGenerico().equals(nombre));
	}
	
	public static void agregar(DispositivoConcreto dispositivo){
		transaction.begin();
		entityManager.persist(dispositivo);
		transaction.commit();
	}
	
	
}
