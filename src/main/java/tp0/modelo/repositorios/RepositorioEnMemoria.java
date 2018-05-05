package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria<T> implements Repositorio<T> {
	private List<T> objetos = new ArrayList<>();

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