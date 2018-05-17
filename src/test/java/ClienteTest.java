import java.util.Arrays;

import org.junit.*;

import tp0.modelo.Apagado;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.DispositivoInteligente;
import tp0.modelo.Encendido;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ClienteTest {

	// Tests para probar el comportamiento del cliente

	Cliente nico;
	DispositivoInteligente dispositivo1;
	DispositivoInteligente dispositivo2;
	DispositivoInteligente dispositivo3;
	DispositivoInteligente dispositivo4;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new DispositivoInteligente("Heladera", 150);
		dispositivo1.setEstado(new Encendido());
		dispositivo2 = new DispositivoInteligente("Lavarropas", 150);
		dispositivo2.setEstado(new Apagado());
		dispositivo3 = new DispositivoInteligente("Tostadora", 50);
		dispositivo3.setEstado(new Apagado());
		dispositivo4 = new DispositivoInteligente("Licuadora", 50);
		dispositivo4.setEstado(new Apagado());
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);
		categoria2 = new Categoria("R2", 25.0, 0.85, 100, 200);
		categorias = new RepositorioEnMemoria<Categoria>();

		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01",
			  "R1", Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
		nico.setRepositorioCategorias(categorias);
		nico.obtenerCategoria();
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
	public void testNicoEsCategorizadoAR1Inicialmente() {
		Assert.assertEquals("R1", nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		nico.asignarCategoria();
		Assert.assertEquals("R2", nico.getCategoria().getNombre());
	}
}