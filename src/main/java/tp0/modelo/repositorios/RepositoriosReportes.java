package tp0.modelo.repositorios;

import java.util.List;
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

		List<ReporteConsumoCliente> reportesConsumoCliente = 
				entityManager
				.createQuery("from ReporteConsumoCliente ORDER BY id DESC")
				.getResultList();	
		repositorioReporteConsumoCliente.agregar(reportesConsumoCliente);
	}
	
	public static ReporteConsumoCliente findReporteConsumoCliente(Integer documento){
		return repositorioReporteConsumoCliente.encontrar(rep -> rep.getDNI().equals(documento));
	}
	
}
