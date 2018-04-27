import java.util.Arrays;

import org.junit.*;

import tp0.modelo.Categoria;
import tp0.modelo.Cliente;
import tp0.modelo.Dispositivo;

public class ClienteTest{
	
	//Tests para probar el comportamiento del cliente
	
	Cliente nico;
	Dispositivo dispositivo1;
	Dispositivo dispositivo2;
	Dispositivo dispositivo3;
	Dispositivo dispositivo4;
	Categoria categoriaPrueba;
	
	@Before
	public void setUp() throws Exception{
		dispositivo1 = new Dispositivo("Heladera", 10, true);
		dispositivo2 = new Dispositivo("Lavarropas", 10, true);
		dispositivo3 = new Dispositivo("Tostadora", 10, false);
		dispositivo4 = new Dispositivo("Licuadora", 10, false);
		nico = new Cliente("Nicolás", "Fonseca", "DNI", 39068888, "1141693939", "Calle Falsa 123", "2018-01-01", "R2", Arrays.asList(dispositivo1, dispositivo2, dispositivo3, dispositivo4));
		categoriaPrueba = new Categoria("R1", 18.76, 0.644, 0, 150);
	}
	
	/*Comento el test porque no existe más el método
	 * 
	 * @Test
	public void testCambiarDispositivoClienteNico() {
		nico.setCategoria(categoriaPrueba);
		Assert.assertTrue(nico.getCategoria().getId() == "R1");
	}*/
	
	@Test
	public void testNicoTieneAlgunDispositivoEncendido() {
		Assert.assertTrue(nico.tieneAlgunDispositivoEncendido());
	}
	
	@Test
	public void testNicoTiene4Dispositivos() {
		Assert.assertTrue(nico.cantidadDispositivosTotal() == 4);
	}
	
	@Test
	public void testNicoTiene2DIspositivosEncendidos() {
		Assert.assertTrue(nico.cantidadDispositivosEncendidos() == 2);

	}
	@Test
	public void testNicoTiene2DIspositivosApagados() {
		Assert.assertTrue(nico.cantidadDispositivosApagados() == 2);

	}
	
	@Test
	public void testNicoConsume20KWPorHora() {
		Assert.assertTrue(nico.consumoEstimadoTotal() == 20);
	}
}