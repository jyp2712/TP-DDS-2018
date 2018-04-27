import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.*;

import tp0.modelo.Administrador;
import tp0.modelo.repositorios.Repositorios;

public class JsonAdministradorTest{
	
	//Tests para probar la carga de datos desde administradores.json
	List<Administrador> administradores = Repositorios.obtenerRepositorioDeAdministradores().todos();
	Administrador administrador, juan;

	@Before
	public void setUp() throws Exception {
		administrador = new Administrador(101010, "Juan", "Lopez", "Calle Falsa 123", "2017-07-01");
		juan = administradores.stream().filter(administrador -> administrador.getId().equals(101010)).findFirst().get();
	}
	
	@Test
	public void testJuanNombre() {
		Assert.assertTrue(juan.getNombre().equals(administrador.getNombre()));
	}
	
	@Test
	public void testJuanApellido() {
		Assert.assertTrue(juan.getApellido().equals(administrador.getApellido()));
	}

	@Test
	public void testJuanDomicilio() {
		Assert.assertTrue(juan.getDomicilio().equals(administrador.getDomicilio()));
	}
	
	@Test
	public void testJuanFechaAltaSistema() {
		Assert.assertEquals(juan.getFechaAltaSistema(), administrador.getFechaAltaSistema());
	}
	
	@Test
	public void testJuanAntiguedad() {
		Assert.assertTrue(juan.antiguedadAdministrador() == administrador.antiguedadAdministrador());
	}
}