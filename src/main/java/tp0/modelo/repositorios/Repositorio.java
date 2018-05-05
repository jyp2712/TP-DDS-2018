package tp0.modelo.repositorios;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//SM: No se si el tipo List es lo suficientemente generico para este caso
public interface Repositorio<T> {
	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	List<T> todos();

	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	void agregar(T objeto);

	// SM: Va a depender usualmente del tipo de repositorio. Mas adelante cuando
	// tengamos persistencia
	void remover(T objeto);

	default List<T> filtrar(Predicate<T> predicado) {
		return todos().stream().filter(predicado).collect(Collectors.<T>toList());
	}

	// SM TODO: En caso de no encontrarlo deberiamos tirar una excepcion pero por
	// ahora me conformo
	default T encontrar(Predicate<T> predicado) {
		return filtrar(predicado).stream().findFirst().orElse(null);
	}

	default void agregar(Collection<T> objetos) {
		objetos.forEach(objeto -> this.agregar(objeto));
	}

	default void remover(Collection<T> objetos) {
		objetos.forEach(objeto -> this.remover(objeto));
	}

	default void remover(Predicate<T> predicado) {
		filtrar(predicado).forEach(object -> this.remover(object));
	}
}