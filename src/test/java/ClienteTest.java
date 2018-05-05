import java.util.Arrays;

import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.Dispositivo;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ClienteTest {

	// Tests para probar el comportamiento del cliente

	Cliente nico;
	Dispositivo dispositivo1;
	Dispositivo dispositivo2;
	Dispositivo dispositivo3;
	Dispositivo dispositivo4;
	Categoria categoria;
	Repositorio<Categoria> categorias;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new Dispositivo("Heladera", 150, true);
		dispositivo2 = new Dispositivo("Lavarropas", 150, false);
		dispositivo3 = new Dispositivo("Tostadora", 50, false);
		dispositivo4 = new Dispositivo("Licuadora", 50, false);
		categoria = new Categoria("R1", 18.76, 0.644, 0, 150);
		categorias = new RepositorioEnMemoria<Categoria>();

		categorias.agregar(categoria);

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01",
				categorias, "R1", Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
	}

	@Test
	public void testNicoTieneAlgunDispositivoEncendido() {
		Assert.assertTrue(nico.tieneAlgunDispositivoEncendido());
	}

	@Test
	public void testNicoTiene4Dispositivos() {
		Assert.assertEquals(4, nico.cantidadDispositivosTotal());
	}

	@Test
	public void testNicoTiene1DIspositivoEncendido() {
		Assert.assertEquals(1, nico.cantidadDispositivosEncendidos());
	}

	@Test
	public void testNicoTiene3DIspositivosApagados() {
		Assert.assertEquals(3, nico.cantidadDispositivosApagados());
	}

	@Test
	public void testNicoConsume150KWPorHora() {
		Assert.assertEquals(150, nico.consumoEstimadoTotal(), 0);
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		Assert.assertEquals("R1", nico.getCategoria().getNombre());
	}
}