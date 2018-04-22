package tp0.modelo;

import java.util.List;

public class Categorizador {
	private List<Categoria> FCategorias;
	
	public void AddCategoria(Categoria Categoria) {
		FCategorias.add(Categoria);	
	}
	
	public void RemoveCategoria(Categoria Categoria) {
		FCategorias.remove(Categoria);
	}
	
	public void AsignarCategoria(Cliente Cliente) {
		Cliente.setCategoria(this.DeterminarCategoria(Cliente.Consumo()));
	}
	
	public Categoria DeterminarCategoria(double Consumo) {
		return FCategorias.stream().
					filter(categoria -> isBetween(Consumo, categoria.getValorMinimo(), categoria.getValorMaximo())).
					findFirst().
					orElse(null);
	}
	
	public boolean isBetween(Double Numero1, Double Numero2, Double Numero3)
	{
		return Numero1 >= Numero2 && Numero1 <= Numero3;
	}
}
