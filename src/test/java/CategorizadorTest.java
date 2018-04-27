import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Categorizador;

public class CategorizadorTest {

	//SM: Tests muy simples para probar el funcionamiento basico y entender testing
	Categorizador categorizador = Categorizador.getCategorizador();


	@Test
	public void testDeterminarCategoriaR1() {
		Categoria categoria = categorizador.determinarCategoria(100);
		Assert.assertTrue(categoria.getNombre() == "R1");
	}
	
	@Test
	public void testDeterminarCategoriaR2() {
		Categoria categoria = categorizador.determinarCategoria(200);
		Assert.assertTrue(categoria.getNombre() == "R2");
	}
	
	@Test
	public void testDeterminarCategoriaR3() {
		Categoria categoria = categorizador.determinarCategoria(350);
		Assert.assertTrue(categoria.getNombre() == "R3");
	}
	
	@Test
	public void testDeterminarCategoriaR4() {
		Categoria categoria = categorizador.determinarCategoria(425);
		Assert.assertTrue(categoria.getNombre() == "R4");
	}
	
	@Test
	public void testDeterminarCategoriaR5() {
		Categoria categoria = categorizador.determinarCategoria(475);
		Assert.assertTrue(categoria.getNombre() == "R5");
	}
	
	@Test
	public void testDeterminarCategoriaR6() {
		Categoria categoria = categorizador.determinarCategoria(550);
		Assert.assertTrue(categoria.getNombre() == "R6");
	}
	
	@Test
	public void testDeterminarCategoriaR7() {
		Categoria categoria = categorizador.determinarCategoria(650);
		Assert.assertTrue(categoria.getNombre() == "R7");
	}
	
	@Test
	public void testDeterminarCategoriaR8() {
		Categoria categoria = categorizador.determinarCategoria(1000);
		Assert.assertTrue(categoria.getNombre() == "R8");
	}
	
	@Test
	public void testDeterminarCategoriaR9() {
		Categoria categoria = categorizador.determinarCategoria(5000);
		Assert.assertTrue(categoria.getNombre() == "R9");
	}
	
	@Test
	public void testDevolverCategoriaR1() {
		Categoria categoria = categorizador.getCategoria("R1");
		Assert.assertTrue(categoria.getNombre() == "R1");
	}
	
	@Test
	public void testDevolverCategoriaR2() {
		Categoria categoria = categorizador.getCategoria("R2");
		Assert.assertTrue(categoria.getNombre() == "R2");
	}
	
	@Test
	public void testDevolverCategoriaR3() {
		Categoria categoria = categorizador.getCategoria("R3");
		Assert.assertTrue(categoria.getNombre() == "R3");
	}
	
	@Test
	public void testDevolverCategoriaR4() {
		Categoria categoria = categorizador.getCategoria("R4");
		Assert.assertTrue(categoria.getNombre() == "R4");
	}
	
	@Test
	public void testDevolverCategoriaR5() {
		Categoria categoria = categorizador.getCategoria("R5");
		Assert.assertTrue(categoria.getNombre() == "R5");
	}
	
	@Test
	public void testDevolverCategoriaR6() {
		Categoria categoria = categorizador.getCategoria("R6");
		Assert.assertTrue(categoria.getNombre() == "R6");
	}
	
	@Test
	public void testDevolverCategoriaR7() {
		Categoria categoria = categorizador.getCategoria("R7");
		Assert.assertTrue(categoria.getNombre() == "R7");
	}
	
	@Test
	public void testDevolverCategoriaR8() {
		Categoria categoria = categorizador.getCategoria("R8");
		Assert.assertTrue(categoria.getNombre() == "R8");
	}
	
	@Test
	public void testDevolverCategoriaR9() {
		Categoria categoria = categorizador.getCategoria("R9");
		Assert.assertTrue(categoria.getNombre() == "R9");
	}
}
