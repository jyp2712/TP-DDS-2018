package test.regla;

import org.junit.*;

import test.regla.AccionMock;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.regla.Regla;

public class ReglaTest {

	Regla regla;
	DispositivoInteligente dispositivoInteligente = new DispositivoInteligente(DispositivoConcretoEnum.HELADERA_CONFREEZER.toString(), 150);
	AccionMock accionMock = new AccionMock(dispositivoInteligente);
	SensorMock sensorMock = new SensorMock();
	CondicionMock condicionMock = new CondicionMock(sensorMock);

	@Before
	public void setUp() throws Exception {
		
		regla = new Regla(condicionMock, accionMock);
	}


	@Test
	public void testEjecutarRegla() {
		regla.ejecutar(0);
		Assert.assertTrue(accionMock.getEjecuciones() == 1);
	}

}