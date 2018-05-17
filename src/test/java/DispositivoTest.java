import org.junit.*;

import tp0.modelo.DispositivoEstandar;

public class DispositivoTest {

	// Tests para probar el comportamiento de la CATEGORIA
	DispositivoEstandar dispositivo1;
	DispositivoEstandar dispositivo2;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new DispositivoEstandar("Heladera", 150, true);
		dispositivo2 = new DispositivoEstandar("Lavarropas", 150, false);
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