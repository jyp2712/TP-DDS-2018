package test.repositorio;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp0.modelo.Categoria;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class RepositorioEnMemoriaTest {

	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;

	@Before
	public void setUp() throws Exception {
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);
		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);
		categorias = new RepositorioEnMemoria<Categoria>();
	}

	@Test
	public void testRepositorioAgrega1Elemento() {
		categorias.agregar(categoria1);
		Assert.assertEquals(1, categorias.todos().size());
	}
	
	public void testRepositorioAgrega1ConjuntoDeElementos() {
		categorias.agregar(Arrays.asList(categoria1, categoria2));
		Assert.assertEquals(2, categorias.todos().size());
	}
	
	public void testRepositorioEncuentraSegunCondicion() {
		categorias.agregar(Arrays.asList(categoria1, categoria2));
		Assert.assertEquals("R1", categorias.encontrar(categoria -> categoria.enRango(50.0)).getNombre());
	}
	
	//SM: Por ahora estan bien estos tests porque es lo unico que utilizamos de este repositorio 
}
