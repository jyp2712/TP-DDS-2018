package tp0;

import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.Minutes;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import server.Server;
import tp0.modelo.Cliente;
import tp0.modelo.hogar.CommandOptimizarHogar;
import tp0.modelo.hogar.Hogar;

public class App {


	public static void main(String[] args) {
		new App().startJob();
		new Server().init();
	}

	
	@SuppressWarnings("unchecked")
	private void startJob() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		List<Cliente> clientes = entityManager.createQuery("from Cliente").getResultList();
		
		if(!clientes.isEmpty()) {
			Cliente cliente = clientes.get(0);
			new CommandOptimizarHogar(new Hogar(), cliente.getDispositivos(), Minutes.ONE);
		}
	}

}