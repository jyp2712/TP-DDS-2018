package tp0.modelo.repositorios;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tp0.modelo.dispositivo.Dispositivo;

public class RepositoriosDispositivos implements WithGlobalEntityManager{

	public static RepositoriosDispositivos instancia = new RepositoriosDispositivos();
	
	@SuppressWarnings("unchecked")
	public List<Dispositivo> listar(String id) {
		List<Dispositivo> intel = entityManager().createQuery("from DispositivoInteligente where cliente_id="+id).getResultList();
		List<Dispositivo> estand = entityManager().createQuery("from DispositivoEstandar where cliente_id="+id).getResultList();
		
		return Stream.concat(intel.stream(), estand.stream()).collect(Collectors.toList());
	}
	
}
