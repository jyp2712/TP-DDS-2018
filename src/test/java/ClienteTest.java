import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.*;

import test.dispositivo.HeladeraMock;
import test.dispositivo.LavarropasMock;
import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.repositorios.Repositorio;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class ClienteTest {

	// Tests para probar el comportamiento del cliente

	Cliente nico;
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoEstandar dispositivoEstandar1;
	DispositivoEstandar dispositivoEstandar2;
	Categoria categoria1;
	Categoria categoria2;
	Repositorio<Categoria> categorias;
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	DateTime fechaActual;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	List<DispositivoEstandar> dispositivosEstandares = new ArrayList<DispositivoEstandar>();
	List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<DispositivoInteligente>();

	@Before
	public void setUp() throws Exception {
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
				new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
				new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		dispositivoInteligente2.setEstado(Estado.APAGADO);
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);
		dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
		dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);
		dispositivoEstandar2 = new DispositivoEstandar("VENTILADOR_PIE", 24, 2);
		dispositivoEstandar2.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivosEstandares.addAll(Arrays.asList(dispositivoEstandar1, dispositivoEstandar2));
		dispositivosInteligentes.addAll(Arrays.asList(dispositivoInteligente1, dispositivoInteligente2));


		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);

		categoria2 = new Categoria("R2", 25.0, 0.85, 100, Double.POSITIVE_INFINITY);

		categorias = new RepositorioEnMemoria<Categoria>();
		categorias.agregar(Arrays.asList(categoria1, categoria2));

		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R1",
				dispositivosEstandares, dispositivosInteligentes, 0);
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
	public void testNicoTiene1DispositivoEncendido() {
		Assert.assertEquals(1, nico.cantidadDispositivosEncendidos());
	}

	@Test
	public void testNicoTiene1DispositivoApagado() {
		Assert.assertEquals(1, nico.cantidadDispositivosApagados());
	}

	@Test
	public void testNicoConsume3HorasLosDispositivosEstandares() {
		Assert.assertEquals(3, nico.getDispositivosEstandar().stream()
				.mapToDouble(dispositivo -> dispositivo.getHorasDeConsumo()).sum(), 0);
	}

	@Test
	public void testNicoConsume48KWxHoraDispositivosEstandares() {
		Assert.assertEquals(48,
				nico.getDispositivosEstandar().stream().mapToDouble(dispositivo -> dispositivo.getKwXHora()).sum(), 0);
	}

	@Test
	public void testNicoConsume300KWxHoraDispositivosInteligentes() {
		Assert.assertEquals(300,
				nico.getDispositivosInteligentes().stream().mapToDouble(dispositivo -> dispositivo.getKwXHora()).sum(),
				0);
	}

	@Test
	public void testNicoTiene2DispositivosEstandares() {
		Assert.assertEquals(2, nico.getDispositivosEstandar().stream().count(), 0);
	}

	@Test
	public void testDispositivoEstandar1Consume24KWXHora() {
		Assert.assertEquals(24, dispositivoEstandar1.getKwXHora(), 0);
	}

	@Test
	public void testNicoConsumeTotalmente1028() {
		fechaActual = DateTime.now();
		Assert.assertEquals(1028, nico.consumoTotal(fechaActual.minusHours(1), fechaActual), 0);
	}

	@Test
	public void testNicoEsCategorizadoAR1Inicialmente() {
		Assert.assertEquals(categoria1.getNombre(), nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		nico.asignarCategoria();
		Assert.assertEquals(categoria2.getNombre(), nico.getCategoria().getNombre());
	}

	@Test
	public void testNicoRegistrarDispositivoEstandar1YQuedaCon15Puntos() {
		nico.registrarDispositivoInteligente(dispositivoInteligente1);
		Assert.assertEquals(15, nico.getPuntos(), 0);
	}

}