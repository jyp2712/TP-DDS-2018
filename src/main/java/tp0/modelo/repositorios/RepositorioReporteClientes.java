package tp0.modelo.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tp0.modelo.reportes.ReporteConsumoCliente;

public class RepositorioReporteClientes implements WithGlobalEntityManager {

	public static RepositorioReporteClientes instancia = new RepositorioReporteClientes();
	
	@SuppressWarnings("unchecked")
	public List<ReporteConsumoCliente> listar() {
		List<ReporteConsumoCliente> reporte = entityManager().
				createQuery("from ReporteConsumoCliente ").getResultList();
		return reporte;
	}
	
}
