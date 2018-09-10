package test.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.Estado;
import tp0.modelo.json.DecodificadorJson;
import tp0.modelo.repositorios.RepositorioEnMemoria;
import tp0.modelo.repositorios.fuentes.FuenteArchivo;

public class JsonClienteTest {

	// Tests para probar la carga de datos desde cliente.json
	RepositorioEnMemoria<Categoria> repositorioDeCategorias = new RepositorioEnMemoria<Categoria>();
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	
	DecodificadorJson decodificador = new DecodificadorJson(new FuenteArchivo("clientes.json"), Cliente.class);
	List<Cliente> clientes = new ArrayList<>();
	Cliente nicolas;
	Cliente nico;
	DispositivoInteligente dispositivoInteligente1, dispositivoInteligente2;
	DispositivoEstandar dispositivoEstandar1, dispositivoEstandar2;
	List<DispositivoInteligente> dispositivosInteligentes;
	List<DispositivoEstandar> dispositivosEstandares;
	DispositivoInteligente disp1;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		clientes.addAll((Collection<? extends Cliente>) decodificador.leer());
		repositorioDeCategorias.agregar(
				Arrays.asList(new Categoria("R1", 18.76, 0.644, 0, 150), new Categoria("R2", 35.32, 0.644, 150, 325),
						new Categoria("R3", 60.71, 0.681, 325, 400), new Categoria("R4", 71.74, 0.738, 400, 450),
						new Categoria("R5", 110.38, 0.794, 450, 500), new Categoria("R6", 220.75, 0.832, 500, 600),
						new Categoria("R7", 443.59, 0.851, 600, 700), new Categoria("R8", 545.96, 0.851, 700, 1400),
						new Categoria("R9", 887.19, 0.851, 1400, Double.POSITIVE_INFINITY)));
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
				new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
				new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		clientes.stream().forEach(cliente -> cliente.setRepositorioCategorias(repositorioDeCategorias));
		clientes.stream().forEach(cliente -> cliente.obtenerCategoria());
		
		dispositivoInteligente1 = new DispositivoInteligente("HELADERA_CONFREEZER", 150);
		dispositivoInteligente1.setEstado(Estado.ENCENDIDO);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivoInteligente2 = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		dispositivoInteligente2.setEstado(Estado.APAGADO);
		dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoEstandar1 = new DispositivoEstandar("TELEVISOR_TUBO_21", 24, 1);
		dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoEstandar2 = new DispositivoEstandar("VENTILADOR_PIE", 24, 2);
		dispositivoEstandar2.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivosEstandares = Arrays.asList(dispositivoEstandar1, dispositivoEstandar2);
		dispositivosInteligentes = Arrays.asList(dispositivoInteligente1, dispositivoInteligente2);

		nicolas = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R2",
				dispositivosEstandares, dispositivosInteligentes, 0);
		nicolas.setRepositorioCategorias(repositorioDeCategorias);
		nicolas.obtenerCategoria();
	
		nico = clientes.stream().filter(cliente -> cliente.getDocumento().equals(39068888)).findFirst().get();

		disp1 = nico.getDispositivosInteligentes().stream()
				.filter(dispositivo -> dispositivo.getNombreGenerico().equals(dispositivoInteligente1.getNombreGenerico()))
				.findFirst().get();
	}

	@Test
	public void testNicoNombre() {
		Assert.assertEquals(nico.getNombre(), nicolas.getNombre());
	}

	@Test
	public void testNicoApellido() {
		Assert.assertEquals(nico.getApellido(), nicolas.getApellido());
	}

	@Test
	public void testNicoTipoDocumento() {
		Assert.assertEquals(nico.getTipoDoc(), nicolas.getTipoDoc());

	}

	@Test
	public void testNicoNumeroDeDocumento() {
		Assert.assertEquals(39068888, nico.getDocumento(), 0);

	}

	@Test
	public void testNicoTelefono() {
		Assert.assertEquals(nico.getTel(), nicolas.getTel());
	}

	@Test
	public void testNicoDomicilio() {
		Assert.assertEquals(nico.getDomicilioServicio(), nicolas.getDomicilioServicio());
	}

	@Test
	public void testNicoFechaAltaServicio() {
		Assert.assertEquals(nico.getFechaAltaServicio(), nicolas.getFechaAltaServicio());
	}

	@Test
	public void testNicoCategoria() {
		Assert.assertEquals("R2", nico.getCategoria().getNombre(), nicolas.getCategoria().getNombre());
		Assert.assertEquals(35.32, nico.getCategoria().getCargoFijo(), nicolas.getCategoria().getCargoFijo());
		Assert.assertEquals(0.644, nico.getCategoria().getCargoVariable(), nicolas.getCategoria().getCargoVariable());
	}

	@Test
	public void testNicoDispositivos() {
		Assert.assertEquals(8, nico.cantidadDispositivosTotal(), nicolas.cantidadDispositivosTotal());
		Assert.assertEquals(disp1.getNombreGenerico(), disp1.getNombreGenerico(),
				dispositivoInteligente1.getNombreGenerico());
		Assert.assertEquals(10, disp1.getKwXHora(), dispositivoInteligente1.getKwXHora());
	}
}