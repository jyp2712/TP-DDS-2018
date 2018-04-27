package tp0.modelo;

import java.util.Arrays;
import java.util.List;

public class Categorizador {

	private static final Categorizador categorizador = new Categorizador();

	private Categorizador() {

	}

	public static Categorizador getCategorizador() {
		return categorizador;
	}
	
	//TODO: Crear un bloque de manera tal que al instanciar si hay error, arroja una excepcion

	// SM: El nombre FCategorias es por algo? Una convencion?
	// Se que esta inicializacion se ve fea pero es lo mas simple hasta que tengamos
	// mas informacion.
	private List<Categoria> categorias = Arrays.asList(new Categoria("R1", 18.76, 0.644, 0, 150),
			new Categoria("R2", 35.32, 0.644, 150, 325), new Categoria("R3", 60.71, 0.681, 325, 400),
			new Categoria("R4", 71.74, 0.738, 400, 450), new Categoria("R5", 110.38, 0.794, 450, 500),
			new Categoria("R6", 220.75, 0.832, 500, 600), new Categoria("R7", 443.59, 0.851, 600, 700),
			new Categoria("R8", 545.96, 0.851, 700, 1400),
			new Categoria("R9", 887.19, 0.851, 1400, Double.POSITIVE_INFINITY));

	/*
	 * public void addCategoria(Categoria Categoria) { FCategorias.add(Categoria); }
	 */

	/*
	 * public void removeCategoria(Categoria Categoria) {
	 * FCategorias.remove(Categoria); }
	 */

	// SM: Esta funcion es un toque discutible porque no lo especifica aun. Dice que
	// se tiene que recategorizar pero no especifica
	// muchos mas que eso... Por ahora opino de dejarlo y ver como avanzan los
	// requisistos.



	public Categoria determinarCategoria(double Consumo) {
		return categorias.stream()
				.filter(categoria -> isBetween(Consumo, categoria.getConsumoMinimo(), categoria.getConsumoMaximo()))
				.findFirst().orElse(null);
	}

	private boolean isBetween(Double Numero1, Double Numero2, Double Numero3) {
		return Numero1 >= Numero2 && Numero1 <= Numero3;
	}

	public Categoria getCategoria(String nombre) {
		return categorias.stream().filter(categoria -> categoria.getNombre() == nombre).findFirst().orElse(null);
	}

}
