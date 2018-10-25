package tp0.modelo.repositorios;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria<T> extends Repositorio<T> {
	private List<T> objetos = new ArrayList<>();

	@Override
	public List<T> todos() {
		return objetos;
	}

	@Override
	public void agregar(T objeto) {
		objetos.add(objeto);
	}

	@Override
	public void remover(T objeto) {
		objetos.remove(objeto);
	}
	
	public void setRepositorio(List<T> objetos) {
		this.objetos = objetos;
	}
}