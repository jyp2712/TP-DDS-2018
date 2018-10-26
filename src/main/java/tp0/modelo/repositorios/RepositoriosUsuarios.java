package tp0.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Administrador;
import tp0.modelo.Cliente;

public class RepositoriosUsuarios {

	private static final RepositorioEnMemoria<Cliente> repositorioClientes = new RepositorioEnMemoria<Cliente>();
	private static final RepositorioEnMemoria<Administrador> repositorioAdmin = new RepositorioEnMemoria<Administrador>();
	static EntityManager entityManager;
	static EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public static void cargarClientes() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		List<Cliente> clientes = entityManager.createQuery("from Cliente").getResultList();
		repositorioClientes.setRepositorio(clientes);

	}
	
	@SuppressWarnings("unchecked")
	public void cargarAdministradores() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		List<Administrador> administradores = entityManager.createQuery("from Administrador").getResultList();
		repositorioAdmin.setRepositorio(administradores);

	}
	
	public static Cliente findCliente(String user){
		return repositorioClientes.encontrar(c -> c.getUser().equals(user));
	}
	
	public static Administrador findAdmin(String user) {
		return repositorioAdmin.encontrar(c -> c.getAdmin().equals(user));
	}
	
	public static List<Cliente> listarClientes() {
		return repositorioClientes.todos();
	}
}
