package tp0.modelo.repositorios;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//SM: No se si el tipo List es lo suficientemente generico para este caso
public abstract class Repositorio<T> {
	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	public abstract List<T> todos();

	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	public abstract void agregar(T objeto);

	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	public abstract void remover(T objeto);

	public List<T> filtrar(Predicate<T> predicado) {
		return todos().stream().filter(predicado).collect(Collectors.<T>toList());
	}

	// SM TODO: En caso de no encontrarlo deberiamos tirar una excepcion pero por
	// ahora me conformo
	public T encontrar(Predicate<T> predicado) {
		return filtrar(predicado).stream().findFirst().orElse(null);
	}

	public void agregar(Collection<T> objetos) {
		objetos.forEach(objeto -> this.agregar(objeto));
	}

	public void remover(Collection<T> objetos) {
		objetos.forEach(objeto -> this.remover(objeto));
	}

	public void remover(Predicate<T> predicado) {
		filtrar(predicado).forEach(object -> this.remover(object));
	}
}