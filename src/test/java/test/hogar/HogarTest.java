package test.hogar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import test.dispositivo.LavarropasMock;
import test.regla.AccionMock;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.regla.Accion;
import tp0.modelo.hogar.Hogar;
import tp0.modelo.hogar.OptimizadorSimplex;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class HogarTest {

	Hogar hogar;
	OptimizadorSimplex optimizador;
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	DispositivoInteligente lavarropas;
	DispositivoEstandar televisor;
	List<Dispositivo> dispositivos = new ArrayList<>();
	List<Dispositivo> disp = new ArrayList<>();
	List<Accion> acciones = new ArrayList<>();
	double[] resultado;
	AccionMock accion;
	LavarropasMock lavarropasMock = new LavarropasMock();
	SensorHogarMock sensorMock = new SensorHogarMock();

	@Before
	public void setUp() throws Exception {
	
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto(DispositivoConcretoEnum.HELADERA_CONFREEZER, 0.09, 0, 0),
						new DispositivoConcreto(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG, 0.175, 6, 30),
				new DispositivoConcreto(DispositivoConcretoEnum.TELEVISOR_TUBO_21, 0.075, 90, 360),
				new DispositivoConcreto(DispositivoConcretoEnum.VENTILADOR_PIE, 0.09, 120, 360)));
		
		lavarropas = new DispositivoInteligente(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG.toString(), 150);
		lavarropas.setDispositivoGenerico(repositorioDeDispositivos);
		lavarropas.setDispositivoFisico(lavarropasMock);
		lavarropas.setSensorAdapter(sensorMock);
		
		televisor = new DispositivoEstandar(DispositivoConcretoEnum.TELEVISOR_TUBO_21.toString(), 100, 10);
		televisor.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivos.addAll(Arrays.asList(lavarropas, televisor));
		optimizador = new OptimizadorSimplex();
		accion = new AccionMock(lavarropas);
		
		hogar = new Hogar("hola");
		hogar.setOptimizador(optimizador);
		hogar.actualizarDispositivos(dispositivos);
		
		disp.addAll(Arrays.asList(lavarropas));
		acciones.addAll(Arrays.asList(accion));
	}

	@Test
	public void testDireccion() {
		Assert.assertTrue(hogar.getDireccion().equals("hola"));
	}
	
	@Test
	public void testOptimizacion() {
		double[] resultado = hogar.optimizar();
		Assert.assertEquals(televisor.getUsoMaximo(), resultado[televisor.getNombreGenericoPosicion()], 0);
		Assert.assertEquals(lavarropas.getUsoMaximo(), resultado[lavarropas.getNombreGenericoPosicion()], 0);
	}
	
	@Test
	public void testOptimizacionConAccion() {
		hogar.actualizarDispositivos(disp);
		hogar.configurarReglas(acciones);
		hogar.activarAccionesAuto();
		hogar.optimizar();
		Assert.assertEquals(1, accion.getEjecuciones());
	}
	
}