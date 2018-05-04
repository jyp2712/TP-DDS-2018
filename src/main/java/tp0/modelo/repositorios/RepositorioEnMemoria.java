package tp0.modelo.repositorios;

import java.util.List;

public abstract class RepositorioEnMemoria<T> implements Repositorio<T> {
	private List<T> objetos;

	public List<T> todos() {
		return objetos;
	}

	public void agregar(T objeto) {
		objetos.add(objeto);
	}

	public void remover(T objeto) {
		objetos.remove(objeto);
	}
}