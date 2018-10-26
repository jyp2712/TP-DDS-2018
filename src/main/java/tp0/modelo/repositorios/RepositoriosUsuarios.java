package tp0.modelo.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Administrador;
import tp0.modelo.Cliente;

public class RepositoriosUsuarios {

	public static final RepositorioEnMemoria<Cliente> repositorioClientes = new RepositorioEnMemoria<Cliente>();
	public static final RepositorioEnMemoria<Administrador> repositorioAdmin = new RepositorioEnMemoria<Administrador>();
	static EntityManager entityManager;
	static EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public static void cargarClientes() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		repositorioClientes.setRepositorio(entityManager.createQuery("from Cliente").getResultList());
	}

	@SuppressWarnings("unchecked")
	public static void cargarAdministradores() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
	
		repositorioAdmin.setRepositorio(entityManager.createQuery("from Administrador").getResultList());
	}
	
	public static Cliente findCliente(String user){
		return repositorioClientes.encontrar(c -> c.getUser().equals(user));
	}

	public static Administrador findAdmin(String admin){
		return repositorioAdmin.encontrar(a -> a.getAdmin().equals(admin));
	
	}
}
