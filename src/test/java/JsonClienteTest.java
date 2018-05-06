import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.DecodificadorJson;
import tp0.modelo.Dispositivo;
import tp0.modelo.repositorios.RepositorioEnMemoria;
import tp0.modelo.repositorios.fuentes.FuenteArchivo;

public class JsonClienteTest{
	
	//Tests para probar la carga de datos desde cliente.json
	RepositorioEnMemoria<Categoria> repositorioDeCategorias = new RepositorioEnMemoria<Categoria>();
	DecodificadorJson decodificador = new DecodificadorJson(new FuenteArchivo("clientes.json"), Cliente.class);
	List<Cliente> clientes = new ArrayList<>();
	Cliente nicolas;
	Cliente nico;
	Dispositivo dispositivo1, dispositivo2, dispositivo3, dispositivo4;
	List<Dispositivo> dispositivos;
	Dispositivo disp1;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception{
		clientes.addAll((Collection<? extends Cliente>) decodificador.leer());
		repositorioDeCategorias.agregar(Arrays.asList(new Categoria("R1", 18.76, 0.644, 0, 150),
				new Categoria("R2", 35.32, 0.644, 150, 325), new Categoria("R3", 60.71, 0.681, 325, 400),
				new Categoria("R4", 71.74, 0.738, 400, 450), new Categoria("R5", 110.38, 0.794, 450, 500),
				new Categoria("R6", 220.75, 0.832, 500, 600), new Categoria("R7", 443.59, 0.851, 600, 700),
				new Categoria("R8", 545.96, 0.851, 700, 1400),
				new Categoria("R9", 887.19, 0.851, 1400, Double.POSITIVE_INFINITY)));
		clientes.stream().forEach(cliente -> cliente.setRepositorioCategorias(repositorioDeCategorias));
		clientes.stream().forEach(cliente -> cliente.obtenerCategoria());
		dispositivo1 = new Dispositivo("Heladera", 10, true);
		dispositivo2 = new Dispositivo("Lavarropas", 10, true);
		dispositivo3 = new Dispositivo("Tostadora", 10, false);
		dispositivo4 = new Dispositivo("Licuadora", 10, false);
		dispositivos = Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4);
		nicolas = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R2", dispositivos);
		nicolas.setRepositorioCategorias(repositorioDeCategorias);
		nicolas.obtenerCategoria();
		nico = clientes.stream().filter(cliente -> cliente.getDocumento().equals(39068888)).findFirst().get();
		disp1 = nico.getDispositivos().stream().filter(dispositivo -> dispositivo.getNombreGenerico().equals(dispositivo1.getNombreGenerico())).findFirst().get();
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
		Assert.assertTrue(nico.getTipoDoc() == nicolas.getTipoDoc());

	}
	@Test
	public void testNicoNumeroDeDocumento() {
		Assert.assertTrue(nico.getDocumento() == 39068888);

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
		Assert.assertEquals("Nicolas", nico.getCategoria().getNombre(), nicolas.getCategoria().getNombre());
		Assert.assertEquals(35.32, nico.getCategoria().getCargoFijo(), nicolas.getCategoria().getCargoFijo());
		Assert.assertEquals(0.644, nico.getCategoria().getCargoVariable(), nicolas.getCategoria().getCargoVariable());
	}
	
	@Test
	public void testNicoDispositivos() {
		Assert.assertEquals(dispositivos.size(), nico.getDispositivos().size(), nicolas.getDispositivos().size());
		Assert.assertEquals(disp1.getNombreGenerico(), disp1.getNombreGenerico(), dispositivo1.getNombreGenerico());
		Assert.assertTrue(disp1.getEstado() == dispositivo1.getEstado());
		Assert.assertEquals(10, disp1.getKwXHora(), dispositivo1.getKwXHora());		
	}
}