package test.dispositivo;

import org.junit.*;

import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;

public class DispositivoConcretoTest {

	DispositivoConcreto aireAcondicionado;
	DispositivoConcreto heladera;

	@Before
	public void setUp() throws Exception {
		
		aireAcondicionado = new DispositivoConcreto(DispositivoConcretoEnum.AIREACONDICIONADO_3500, 
				0.9, 360, 2000);
		heladera = new DispositivoConcreto(DispositivoConcretoEnum.HELADERA_CONFREEZER, 0.25, 100, 99);
	}

	// Dispositivos Inteligentes

	@Test
	public void testDispositivoConcretoNombre() {
		Assert.assertEquals(DispositivoConcretoEnum.AIREACONDICIONADO_3500, aireAcondicionado.getNombreGenerico());
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
	public void testAireAcondicionadoSoyHeladera() {
		Assert.assertFalse(aireAcondicionado.soyHeladera());
	}
	
	@Test
	public void testHeladeraSoyHeladera() {
		Assert.assertTrue(heladera.soyHeladera());
	}
	
}