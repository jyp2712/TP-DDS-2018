package test.dispositivo;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.*;

import test.regla.AccionMock;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.estado.*;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class DispositivoTest {

	// Tests para probar el comportamiento de la CATEGORIA
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	DispositivoInteligente dispositivoInteligente1;
	DispositivoInteligente dispositivoInteligente2;
	DispositivoInteligente dispositivoInteligente3;
	HeladeraMock heladeraMock = new HeladeraMock();
	LavarropasMock lavarropasMock = new LavarropasMock();
	DispositivoEstandar dispositivoEstandar1;
	DateTime hoy = DateTime.now();
	DateTime ayer = hoy.minusDays(1);

	@Before
	public void setUp() throws Exception {
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto(DispositivoConcretoEnum.HELADERA_CONFREEZER, 0.09, 0, 0),
						new DispositivoConcreto(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG, 0.175, 6, 30),
				new DispositivoConcreto(DispositivoConcretoEnum.TELEVISOR_TUBO_21, 0.075, 90, 360),
				new DispositivoConcreto(DispositivoConcretoEnum.VENTILADOR_PIE, 0.09, 120, 360)));
		
		dispositivoInteligente1 = new DispositivoInteligente(DispositivoConcretoEnum.HELADERA_CONFREEZER.toString(), 150);
		dispositivoInteligente1.setEstado(new Encendido());
		dispositivoInteligente1.setDispositivoFisico(heladeraMock);
		dispositivoInteligente1.setDispositivoGenerico(repositorioDeDispositivos);

		dispositivoInteligente2 = new DispositivoInteligente(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG.toString(), 150);
		dispositivoInteligente2.setEstado(new Apagado());
		dispositivoInteligente2.setDispositivoFisico(lavarropasMock);
		dispositivoInteligente2.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivoEstandar1 = new DispositivoEstandar(DispositivoConcretoEnum.TELEVISOR_TUBO_21.toString(), 24, 10);
		dispositivoEstandar1.setDispositivoGenerico(repositorioDeDispositivos);
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

}