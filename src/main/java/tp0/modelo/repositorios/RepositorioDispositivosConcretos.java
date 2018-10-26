package tp0.modelo.repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import tp0.modelo.dispositivo.DispositivoConcreto;

public class RepositorioDispositivosConcretos implements WithGlobalEntityManager{

	public static RepositorioDispositivosConcretos instancia = new RepositorioDispositivosConcretos();
	
	public void agregar(DispositivoConcreto disp) {
		entityManager().persist(disp);
	}
	
}

