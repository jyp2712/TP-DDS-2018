import org.junit.*;

import tp0.modelo.Categoria;

public class CategoriaTest {

	// Tests para probar el comportamiento de la CATEGORIA
	Categoria categoria1;
	Categoria categoria2;

	@Before
	public void setUp() throws Exception {
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);
		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);
	}

	@Test
	public void testCosumo0EnRangoCategoria1() {
		Assert.assertTrue(categoria1.enRango(0.0));
	}

	@Test
	public void testCosumo0NoEnRangoCategoria2() {
		Assert.assertFalse(categoria2.enRango(0.0));
	}

	@Test
	public void testCosumo50EnRangoCategoria1() {
		Assert.assertTrue(categoria1.enRango(50.0));
	}

	@Test
	public void testCosumo50NoEnRangoCategoria2() {
		Assert.assertFalse(categoria2.enRango(50.0));
	}

	@Test
	public void testCosumo100NoEnRangoCategoria1() {
		Assert.assertFalse(categoria1.enRango(100.0));
	}

	@Test
	public void testCosumo100EnRangoCategoria2() {
		Assert.assertTrue(categoria2.enRango(100.0));
	}
}