package tp0.modelo.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tp0.modelo.dispositivo.Dispositivo;

public class RepositoriosDispositivos implements WithGlobalEntityManager{

	public static RepositoriosDispositivos instancia = new RepositoriosDispositivos();
	
	@SuppressWarnings("unchecked")
	public List<Dispositivo> listar(String id) {
		return entityManager().createQuery("from Dispositivo where cliente_id="+id).getResultList();
	}
	
}
