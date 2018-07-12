package test.dispositivo;

import org.junit.*;

import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;

public class DispositivoConcretoTest {

	DispositivoConcreto dispositivoConcreto;


	@Before
	public void setUp() throws Exception {
		
		dispositivoConcreto = new DispositivoConcreto(DispositivoConcretoEnum.AIREACONDICIONADO_3500, 
				0.9, 360, 2000);
	
	}

	// Dispositivos Inteligentes

	@Test
	public void testDispositivoConcretoNombre() {
		Assert.assertTrue(dispositivoConcreto.getNombreGenerico() == DispositivoConcretoEnum.AIREACONDICIONADO_3500);
	}
	
	@Test
	public void testDispositivoConcretoCoeficiente() {
		Assert.assertTrue(dispositivoConcreto.getCoeficiente() == 0.9);
	}
	
	@Test
	public void testDispositivoConcretoUsoMinimo() {
		Assert.assertTrue(dispositivoConcreto.getUsoMinimo() == 360);
	}
	
	@Test
	public void testDispositivoConcretoUsoMaximo() {
		Assert.assertTrue(dispositivoConcreto.getUsoMaximo() == 2000);
	}

}