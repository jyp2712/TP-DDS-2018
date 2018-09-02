package test.dispositivo;

import org.junit.*;

import tp0.modelo.dispositivo.DispositivoConcreto;

public class DispositivoConcretoTest {

	DispositivoConcreto aireAcondicionado;
	DispositivoConcreto heladera;

	@Before
	public void setUp() throws Exception {
		
		aireAcondicionado = new DispositivoConcreto("AIREACONDICIONADO_3500", 
				0.9, 360, 2000, true);
		heladera = new DispositivoConcreto("HELADERA_CONFREEZER", 0.25, 100, 99, false);
	}

	// Dispositivos Inteligentes

	@Test
	public void testDispositivoConcretoNombre() {
		Assert.assertEquals("AIREACONDICIONADO_3500", aireAcondicionado.getNombreGenerico());
	}
	
	@Test
	public void testDispositivoConcretoCoeficiente() {
		Assert.assertEquals(0.9, aireAcondicionado.getCoeficiente(), 0);
	}
	
	@Test
	public void testDispositivoConcretoUsoMinimo() {
		Assert.assertEquals(360, aireAcondicionado.getUsoMinimo(), 0);
	}
	
	@Test
	public void testDispositivoConcretoUsoMaximo() {
		Assert.assertEquals(2000, aireAcondicionado.getUsoMaximo(), 0);
	}
	
	@Test
	public void testAireAcondicionadoSoyOptimizable() {
		Assert.assertTrue(aireAcondicionado.optimizable());
	}
	
	@Test
	public void testHeladeraSoyOptimizable() {
		Assert.assertFalse(heladera.optimizable());
	}
	
}