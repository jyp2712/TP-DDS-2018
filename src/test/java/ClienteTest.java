import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.*;


import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Apagado;
import tp0.modelo.dispositivo.estado.Encendido;
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
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	TostadoraMock tostadoraMock = new TostadoraMock();
	LicuadoraMock licuadoraMock = new LicuadoraMock();

	@Before
	public void setUp() throws Exception {
		dispositivoInteligente1 = new DispositivoInteligente("Heladera", 150);
		dispositivoInteligente1.setEstado(new Encendido());
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		
		dispositivoInteligente2 = new DispositivoInteligente("Lavarropas", 150);
		dispositivoInteligente2.setEstado(new Apagado());
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);
		
		dispositivoInteligente3 = new DispositivoInteligente("Tostadora", 50);
		dispositivoInteligente3.setEstado(new Apagado());
		dispositivoInteligente3.setDispositivoFisico(tostadoraMock);
		
		dispositivoInteligente4 = new DispositivoInteligente("Licuadora", 50);
		dispositivoInteligente4.setEstado(new Apagado());
		dispositivoInteligente4.setDispositivoFisico(licuadoraMock);
		
		dispositivoEstandar1 = new DispositivoEstandar("Aire acondicionado", 24, 1);
		
		dispositivoEstandar2 = new DispositivoEstandar("Stereo", 24, 2);
		
		dispositivoEstandar3 = new DispositivoEstandar("Cargador", 24, 3);
		
		dispositivoEstandar4 = new DispositivoEstandar("Lavaplatos", 24, 4);
		
		categoria1 = new Categoria("R1", 18.76, 0.644, 0, 100);
		
		categoria2 = new Categoria("R2", 25.0, 0.85, 100, Double.POSITIVE_INFINITY);
		
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
	public void testNicoConsume10HorasLosDispositivosEstandares() {
		Assert.assertEquals(10, nico.getDispositivosEstandar().stream().mapToDouble(dispositivo -> dispositivo.getHorasDeConsumo()).sum(), 0);
	}
	
	@Test
	public void testNicoConsume96KWxHoraDispositivosEstandares() {
		Assert.assertEquals(96, nico.getDispositivosEstandar().stream().mapToDouble(dispositivo -> dispositivo.getkWXHora()).sum(), 96);
	}
	
	@Test
	public void testNicoConsume400KWxHoraDispositivosInteligentes() {
		Assert.assertEquals(400, nico.getDispositivosInteligentes().stream().mapToDouble(dispositivo -> dispositivo.getKwXHora()).sum(),0);
	}
	
	@Test
	public void testNicoTiene4DispositivosEstandares() {
		Assert.assertEquals(4, nico.getDispositivosEstandar().stream().count(),0);
	}
	
	@Test
	public void testDispositivoEstandar1Consume24KWXHora() {
		Assert.assertEquals(24, dispositivoEstandar1.getkWXHora(), 0);
	}
	
	@Test
	public void testNicoConsume10TotalDispositivosEstandares() {
		fechaActual = DateTime.now();
		Assert.assertEquals(10, nico.consumoTotalEstimadoDispositivosEstandares(fechaActual.minusHours(1)), 10);
	}
	
	@Test
	public void testNicoConsume125TotalDispositivosInteligentes() {
		fechaActual = DateTime.now();
		Assert.assertEquals(125, nico.consumoTotalDispositivosInteligentes(fechaActual), 125);
	}
	
	@Test
	public void testNicoConsumeTotalmente125() {
		fechaActual = DateTime.now();
		Assert.assertEquals(125, nico.consumoTotal(fechaActual.minusHours(1)), 125);
	}

	@Test
	public void testNicoEsCategorizadoAR1Inicialmente() {
		Assert.assertEquals(categoria1.getNombre(), nico.getCategoria().getNombre());
	}
	
	@Test
	public void testNicoTiene2Categorias() {
		Assert.assertEquals(2, nico.getRepositorioCategorias().stream().count());
	}

	@Test
	public void testNicoEsRecategorizadoAR2() {
		nico.asignarCategoria();
		Assert.assertEquals(categoria2.getNombre(), nico.getCategoria().getNombre());
	}
}