package tp0.modelo.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import tp0.modelo.Administrador;
import tp0.modelo.Cliente;

public class RepositoriosUsuarios {

	public static final RepositorioEnMemoria<Cliente> repositorioClientes = new RepositorioEnMemoria<Cliente>();
	public static final RepositorioEnMemoria<Administrador> repositorioAdmin = new RepositorioEnMemoria<Administrador>();
	EntityManager entityManager;
	EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public void cargarClientes() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		List<Cliente> clientes = entityManager.createQuery("from Cliente").getResultList();
		repositorioClientes.agregar(clientes);

	}
	
	@SuppressWarnings("unchecked")
	public void cargarAdministradores() {
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();

		List<Administrador> administradores = entityManager.createQuery("from Administrador").getResultList();
		repositorioAdmin.agregar(administradores);

	}
	
	public Cliente findCliente(String user){
		return repositorioClientes.encontrar(c -> c.getUser().equals(user));
	}
	
}
