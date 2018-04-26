import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Categorizador;

public class CategorizadorTest {

	Categorizador categorizador;

	@Before
	public void setUp() throws Exception {
		categorizador = new Categorizador();
	}

	@Test
	public void testDeterminarCategoriaR1() {
		Categoria categoria = categorizador.determinarCategoria(100);
		Assert.assertTrue(categoria.getId() == "R1");
	}
	
	@Test
	public void testDeterminarCategoriaR2() {
		Categoria categoria = categorizador.determinarCategoria(200);
		Assert.assertTrue(categoria.getId() == "R2");
	}
	
	@Test
	public void testDeterminarCategoriaR3() {
		Categoria categoria = categorizador.determinarCategoria(350);
		Assert.assertTrue(categoria.getId() == "R3");
	}
	
	@Test
	public void testDeterminarCategoriaR4() {
		Categoria categoria = categorizador.determinarCategoria(425);
		Assert.assertTrue(categoria.getId() == "R4");
	}
	
	@Test
	public void testDeterminarCategoriaR5() {
		Categoria categoria = categorizador.determinarCategoria(475);
		Assert.assertTrue(categoria.getId() == "R5");
	}
	
	@Test
	public void testDeterminarCategoriaR6() {
		Categoria categoria = categorizador.determinarCategoria(550);
		Assert.assertTrue(categoria.getId() == "R6");
	}
	
	@Test
	public void testDeterminarCategoriaR7() {
		Categoria categoria = categorizador.determinarCategoria(650);
		Assert.assertTrue(categoria.getId() == "R7");
	}
	
	@Test
	public void testDeterminarCategoriaR8() {
		Categoria categoria = categorizador.determinarCategoria(1000);
		Assert.assertTrue(categoria.getId() == "R8");
	}
	
	@Test
	public void testDeterminarCategoriaR9() {
		Categoria categoria = categorizador.determinarCategoria(5000);
		Assert.assertTrue(categoria.getId() == "R9");
	}
}
