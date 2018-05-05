import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.*;

import tp0.modelo.Administrador;
import tp0.modelo.DecodificadorJson;
import tp0.modelo.repositorios.fuentes.FuenteArchivo;

public class JsonAdministradorTest{
	
	//Tests para probar la carga de datos desde administradores.json
	DecodificadorJson decodificador = new DecodificadorJson(new FuenteArchivo("administradores.json"), Administrador.class);
	List<Administrador> administradores = new ArrayList<>();
	Administrador administrador, juan;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		administradores.addAll((Collection<? extends Administrador>) decodificador.leer());
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