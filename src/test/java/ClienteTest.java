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
	Categoria categoriaPrueba;
	Repositorio<Categoria> categorias;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new Dispositivo("Heladera", 150, true);
		dispositivo2 = new Dispositivo("Lavarropas", 150, false);
		dispositivo3 = new Dispositivo("Tostadora", 50, false);
		dispositivo4 = new Dispositivo("Licuadora", 50, false);
		categoriaPrueba = new Categoria("R1", 18.76, 0.644, 0, 500);
		categorias = new RepositorioEnMemoria<Categoria>();

		categorias.agregar(categoriaPrueba);

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01",
				categorias, Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
	}

	@Test
	public void testNicoTieneAlgunDispositivoEncendido() {
		Assert.assertTrue(nico.tieneAlgunDispositivoEncendido());
	}

	@Test
	public void testNicoTiene4Dispositivos() {
		Assert.assertEquals(nico.cantidadDispositivosTotal(), 4);
	}

	@Test
	public void testNicoTiene1DIspositivoEncendido() {
		Assert.assertEquals(nico.cantidadDispositivosEncendidos(), 1);
	}

	@Test
	public void testNicoTiene3DIspositivosApagados() {
		Assert.assertEquals(nico.cantidadDispositivosApagados(), 3);
	}

	@Test
	public void testNicoConsume300KWPorHora() {
		Assert.assertEquals(nico.consumoEstimadoTotal(), 300);
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		Assert.assertTrue(nico.getCategoria().getNombre() == "R1");
	}
}