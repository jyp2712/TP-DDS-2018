import java.util.Arrays;
import java.util.List;

import org.junit.*;

import tp0.modelo.Cliente;
import tp0.modelo.Dispositivo;
import tp0.modelo.repositorios.Repositorios;

public class JsonClienteTest{
	
	//Tests para probar la carga de datos desde cliente.json
	List<Cliente> clientes = Repositorios.obtenerRepositorioDeClientes().todos();
	Cliente nicolas;
	Cliente nico;
	Dispositivo dispositivo1, dispositivo2, dispositivo3, dispositivo4;
	Dispositivo disp1;
	
	@Before
	public void setUp() throws Exception{
		dispositivo1 = new Dispositivo("Heladera", 10, true);
		dispositivo2 = new Dispositivo("Lavarropas", 10, true);
		dispositivo3 = new Dispositivo("Tostadora", 10, false);
		dispositivo4 = new Dispositivo("Licuadora", 10, false);
		List<Dispositivo> dispositivos = Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4);
		nicolas = new Cliente("Nicolas", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R2", dispositivos);
		nico = clientes.stream().filter(cliente -> cliente.getDocumento().equals(39068888)).findFirst().get();
		disp1 = nico.getDispositivos().stream().filter(dispositivo -> dispositivo.getNombreGenerico().equals(dispositivo1.getNombreGenerico())).findFirst().get();
	}
	
	@Test
	public void testNicoNombre() {
		Assert.assertTrue(nico.getNombre().equals(nicolas.getNombre()));
	}
	
	@Test
	public void testNicoApellido() {
		Assert.assertTrue(nico.getApellido().equals(nicolas.getApellido()));
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
		Assert.assertTrue(nico.getTel().equals(nicolas.getTel()));
	}

	@Test
	public void testNicoDomicilio() {
		Assert.assertTrue(nico.getDomicilioServicio().equals(nicolas.getDomicilioServicio()));
	}
	
	@Test
	public void testNicoFechaAltaServicio() {
		Assert.assertEquals(nico.getFechaAltaServicio(), nicolas.getFechaAltaServicio());
	}
	
	@Test
	public void testNicoCategoria() {
		Assert.assertTrue(nico.getCategoria().getNombre().equals(nicolas.getCategoria().getNombre()));
		Assert.assertTrue(nico.getCategoria().getCargoFijo() == nicolas.getCategoria().getCargoFijo());
		Assert.assertTrue(nico.getCategoria().getCargoVariable() == nicolas.getCategoria().getCargoVariable());
	}
	
	@Test
	public void testNicoDispositivos() {
		Assert.assertTrue(nico.getDispositivos().size() == nicolas.getDispositivos().size());
		Assert.assertTrue(disp1.getNombreGenerico().equals(dispositivo1.getNombreGenerico()));
		Assert.assertTrue(disp1.getEstado() == dispositivo1.getEstado());
		Assert.assertTrue(disp1.getKwXHora() == dispositivo1.getKwXHora());		
	}
}