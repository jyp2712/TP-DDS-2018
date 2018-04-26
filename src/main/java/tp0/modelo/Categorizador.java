package tp0.modelo;

import java.util.List;

public class Categorizador {
	private List<Categoria> FCategorias;
	
	public void addCategoria(Categoria Categoria) {
		FCategorias.add(Categoria);	
	}
	
	public void removeCategoria(Categoria Categoria) {
		FCategorias.remove(Categoria);
	}
	
	public void asignarCategoria(Cliente Cliente) {
		Cliente.setCategoria(this.determinarCategoria(Cliente.Consumo()));
	}
	
	public Categoria determinarCategoria(double Consumo) {
		return FCategorias.stream().
					filter(categoria -> isBetween(Consumo, categoria.getConsumoMinimo(), categoria.getConsumoMaximo())).
					findFirst().
					orElse(null);
	}
	
	public boolean isBetween(Double Numero1, Double Numero2, Double Numero3)
	{
		return Numero1 >= Numero2 && Numero1 <= Numero3;
	}

	public static Categoria vincularCategoria(String categoria) {
		//Acá hay que buscar la categoria dentro de FCategorias
		return null;
	}
}
