import org.junit.*;

import tp0.modelo.Dispositivo;

public class DispositivoTest {

	// Tests para probar el comportamiento de la CATEGORIA
	Dispositivo dispositivo1;
	Dispositivo dispositivo2;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new Dispositivo("Heladera", 150, true);
		dispositivo2 = new Dispositivo("Lavarropas", 150, false);
	}

	@Test
	public void testDispositivo1Encendido() {
		Assert.assertTrue(dispositivo1.estaEncendido());
	}

	@Test
	public void testDispositivo2NoEncendido() {
		Assert.assertFalse(dispositivo2.estaEncendido());
	}
}