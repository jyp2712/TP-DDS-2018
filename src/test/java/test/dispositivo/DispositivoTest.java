package test.dispositivo;

import org.joda.time.DateTime;
import org.junit.*;

import test.regla.AccionMock;
import test.regla.CondicionMock;
import test.regla.SensorMock;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.*;
import tp0.modelo.dispositivo.regla.Regla;

public class DispositivoTest {

	// Tests para probar el comportamiento de la CATEGORIA
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoInteligente dispositivoInteligente3;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	TostadoraMock tostadoraMock = new TostadoraMock();
	DispositivoEstandar dispositivoEstandar1;
	DateTime hoy = DateTime.now();
	DateTime ayer = hoy.minusDays(1);

	@Before
	public void setUp() throws Exception {
		dispositivoInteligente1 = new DispositivoInteligente("Heladera", 150);
		dispositivoInteligente1.setEstado(new Encendido());
		dispositivoInteligente1.setEstadoEnum(EstadoEnum.ENCENDIDO);
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);

		dispositivoInteligente2 = new DispositivoInteligente("Lavarropas", 150);
		dispositivoInteligente2.setEstado(new Apagado());
		dispositivoInteligente2.setEstadoEnum(EstadoEnum.APAGADO);
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);

		dispositivoInteligente3 = new DispositivoInteligente("Tostadora", 50);
		dispositivoInteligente3.setEstado(new AhorroEnergia());
		dispositivoInteligente3.setEstadoEnum(EstadoEnum.AHORROENERGIA);
		dispositivoInteligente3.setDispositivoFisico(tostadoraMock);

		dispositivoEstandar1 = new DispositivoEstandar("Televisor", 24, 10);
	}

	// Dispositivos Inteligentes

	@Test
	public void testDispositivoInteligente1Encendido() {
		Assert.assertTrue(dispositivoInteligente1.estaEncendido());
	}

	@Test
	public void testDispositivoInteligente1SeLoEnciendeYNoHaceNada() {
		dispositivoInteligente1.encenderse();
		Assert.assertEquals(new Encendido().getClass(), dispositivoInteligente1.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente1SeLoApagaCorrectamente() {
		dispositivoInteligente1.apagarse();
		Assert.assertEquals(new Apagado().getClass(), dispositivoInteligente1.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente1SeLoAhorraEnergiaCorrectamente() {
		dispositivoInteligente1.ahorrarseEnergia();
		Assert.assertEquals(new AhorroEnergia().getClass(), dispositivoInteligente1.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente2Apagado() {
		Assert.assertTrue(dispositivoInteligente2.estaApagado());
	}

	@Test
	public void testDispositivoInteligente2SeLoApagaYNoHaceNada() {
		dispositivoInteligente2.apagarse();
		Assert.assertEquals(new Apagado().getClass(), dispositivoInteligente2.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente2SeLoEnciendeCorrectamente() {
		dispositivoInteligente2.encenderse();
		Assert.assertEquals(new Encendido().getClass(), dispositivoInteligente2.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente2SeLoAhorraEnergiaCorrectamente() {
		dispositivoInteligente2.ahorrarseEnergia();
		Assert.assertEquals(new AhorroEnergia().getClass(), dispositivoInteligente2.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente3AhorroEnergia() {
		Assert.assertTrue(dispositivoInteligente3.estaEncendido());
	}

	@Test
	public void testDispositivoInteligente3SeLoAhorraEnergiaYNoHaceNada() {
		dispositivoInteligente3.ahorrarseEnergia();
		Assert.assertEquals(new AhorroEnergia().getClass(), dispositivoInteligente3.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente3SeLoEnciendeCorrectamente() {
		dispositivoInteligente3.encenderse();
		Assert.assertEquals(new Encendido().getClass(), dispositivoInteligente3.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente3SeLoApagaCorrectamente() {
		dispositivoInteligente3.apagarse();
		Assert.assertEquals(new Apagado().getClass(), dispositivoInteligente3.getEstado().getClass());
	}

	@Test
	public void testDispositivoInteligente1ConsumoTotal() {
		Assert.assertEquals(25, dispositivoInteligente1.consumoTotal(DateTime.now().minusHours(2), hoy), 0);
	}

	@Test
	public void testDispositivoInteligente2Consumo() {
		Assert.assertEquals(0, dispositivoInteligente2.consumoUltimas(hoy.getHourOfDay()), 0);
	}

	// Dispositivos Estandares

	@Test
	public void testDispositivoEstandar1Consume10PorHoraAproximada() {
		Assert.assertEquals(10, dispositivoEstandar1.consumoPorHoraAproximada(), 0);
	}

	@Test
	public void testDispositivoEstandar1Consume100Durante10Horas() {
		Assert.assertEquals(100, dispositivoEstandar1.consumoUltimas(10), 0);
	}

	@Test
	public void testDispositivoEstandar1ConsumeTotalmente20Durante2Horas() {
		Assert.assertEquals(20, dispositivoEstandar1.consumoTotal(DateTime.now().minusHours(2), DateTime.now()), 0);
	}

	@Test
	public void testDispositivoInteligente1Ejecuta1Accion() {
		AccionMock accionDispositivoInteligente1 = new AccionMock(dispositivoInteligente1);
		dispositivoInteligente1.ejecutar(accionDispositivoInteligente1);
		Assert.assertEquals(1, accionDispositivoInteligente1.getEjecuciones());
	}

	@Test
	public void testRegla() {
		SensorMock sensorMock = new SensorMock();
		CondicionMock condicionMock = new CondicionMock(sensorMock);
		AccionMock accionDispositivoInteligente1 = new AccionMock(dispositivoInteligente1);
		Regla accionProgramada = new Regla(condicionMock, accionDispositivoInteligente1);
		accionProgramada.ejecutar();
		Assert.assertEquals(1, accionDispositivoInteligente1.getEjecuciones());
	}
	
	@Test
	public void testDispositivoInteligenteApagarEnum() {
		dispositivoInteligente1.getEstadoEnum().apagar(dispositivoInteligente1);
		Assert.assertEquals(EstadoEnum.APAGADO, dispositivoInteligente1.getEstadoEnum());		
	}
	
}