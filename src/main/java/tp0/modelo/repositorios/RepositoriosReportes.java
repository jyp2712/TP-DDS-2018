package tp0.modelo.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.reportes.ReporteConsumoCliente;

public class RepositoriosReportes {

	public static final RepositorioEnMemoria<ReporteConsumoCliente> repositorioReporteConsumoCliente = new RepositorioEnMemoria<ReporteConsumoCliente>();
	static EntityManager entityManager;
	static EntityTransaction transaction;
	
	@SuppressWarnings("unchecked")
	public static void cargarReportes() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		repositorioReporteConsumoCliente.setRepositorio(entityManager
				.createQuery("from ReporteConsumoCliente ORDER BY id DESC")
				.getResultList());
	}
	
	public static ReporteConsumoCliente findReporteConsumoCliente(Integer documento){
		return repositorioReporteConsumoCliente.encontrar(rep -> rep.getDNI().equals(documento));
	}
	
}
