import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.*;

import tp0.modelo.Apagado;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.DispositivoInteligente;
import tp0.modelo.DispositivoEstandar;
import tp0.modelo.Encendido;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ClienteTest {

	// Tests para probar el comportamiento del cliente

	Cliente nico;
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoInteligente dispositivoInteligente3;
	DispositivoInteligente dispositivoInteligente4;
	DispositivoEstandar dispositivoEstandar1;
	DispositivoEstandar dispositivoEstandar2;
	DispositivoEstandar dispositivoEstandar3;
	DispositivoEstandar dispositivoEstandar4;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	DateTime fechaActual;

	@Before
	public void setUp() throws Exception {
		dispositivoInteligente1 = new DispositivoInteligente("Heladera", 150);
		dispositivoInteligente1.setEstado(new Encendido());
		dispositivoInteligente2 = new DispositivoInteligente("Lavarropas", 150);
		dispositivoInteligente2.setEstado(new Apagado());
		dispositivoInteligente3 = new DispositivoInteligente("Tostadora", 50);
		dispositivoInteligente3.setEstado(new Apagado());
		dispositivoInteligente4 = new DispositivoInteligente("Licuadora", 50);
		dispositivoInteligente4.setEstado(new Apagado());
		dispositivoEstandar1 = new DispositivoEstandar("Aire acondicionado", 24, 1);
		dispositivoEstandar2 = new DispositivoEstandar("Stereo", 24, 2);
		dispositivoEstandar3 = new DispositivoEstandar("Cargador", 24, 3);
		dispositivoEstandar4 = new DispositivoEstandar("Lavaplatos", 24, 4);
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);
		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);
		categorias = new RepositorioEnMemoria<Categoria>();

		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				Arrays.asList(dispositivoEstandar1, dispositivoEstandar2, dispositivoEstandar3, dispositivoEstandar4),
				Arrays.asList(dispositivoInteligente1, dispositivoInteligente2, dispositivoInteligente3,
						dispositivoInteligente4));
		nico.setRepositorioCategorias(categorias);
		nico.obtenerCategoria();
	}

	@Test
	public void testNicoTieneAlgunDispositivoEncendido() {
		Assert.assertTrue(nico.tieneAlgunDispositivoEncendido());
	}

	@Test
	public void testNicoTiene8Dispositivos() {
		Assert.assertEquals(8, nico.cantidadDispositivosTotal());
	}

	@Test
	public void testNicoTiene1DispositivoEncendido() {
		Assert.assertEquals(1, nico.cantidadDispositivosEncendidos());
	}

	@Test
	public void testNicoTiene3DispositivosApagados() {
		Assert.assertEquals(3, nico.cantidadDispositivosApagados());
	}

	@Test
	public void testNicoConsume10KWPorHora() {
		fechaActual = DateTime.now();
		Assert.assertEquals(10, nico.consumoTotalEstimadoDispositivosEstandares(fechaActual.minusHours(1)), 0);
	}

	@Test
	public void testNicoEsCategorizadoAR1Inicialmente() {
		Assert.assertEquals("R1", nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		nico.asignarCategoria();
		Assert.assertEquals("R2", nico.getCategoria().getNombre());
	}
}