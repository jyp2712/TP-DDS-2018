import org.junit.*;

import tp0.modelo.Apagado;
import tp0.modelo.DispositivoInteligente;
import tp0.modelo.Encendido;

public class DispositivoTest {

	// Tests para probar el comportamiento de la CATEGORIA
	DispositivoInteligente dispositivo1;
	DispositivoInteligente dispositivo2;

	@Before
	public void setUp() throws Exception {
		dispositivo1 = new DispositivoInteligente("Heladera", 150);
		dispositivo2 = new DispositivoInteligente("Lavarropas", 150);
		dispositivo1.setEstado(new Encendido());
		dispositivo2.setEstado(new Apagado());
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