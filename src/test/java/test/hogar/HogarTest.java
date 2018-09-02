package test.hogar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import test.dispositivo.LavarropasMock;
import test.regla.AccionMock;
import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoConcreto;
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
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
				new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
				new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		lavarropas = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		lavarropas.setDispositivoGenerico(repositorioDeDispositivos);
		lavarropas.setDispositivoFisico(lavarropasMock);
		lavarropas.setSensorAdapter(sensorMock);
		
		televisor = new DispositivoEstandar("TELEVISOR_TUBO_21", 100, 10);
		televisor.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivos.addAll(Arrays.asList(lavarropas, televisor));
		optimizador = new OptimizadorSimplex();
		accion = new AccionMock(lavarropas);
		
		hogar = new Hogar("hola");
		
		disp.addAll(Arrays.asList(lavarropas));
		acciones.addAll(Arrays.asList(accion));
	}

	@Test
	public void testDireccion() {
		Assert.assertTrue(hogar.getDireccion().equals("hola"));
	}
	
	@Test
	public void testOptimizacion() {
		double[] resultado = hogar.optimizar(dispositivos);
		int posTele = dispositivos.indexOf(televisor);
		int posLavarropas = dispositivos.indexOf(lavarropas);
		Assert.assertEquals(televisor.getUsoMaximo(), resultado[posTele], 0);
		Assert.assertEquals(lavarropas.getUsoMaximo(), resultado[posLavarropas], 0);
	}
	
	@Test
	public void testOptimizacionConAccion() {
		hogar.configurarReglas(acciones);
		hogar.activarAccionesAuto();
		hogar.optimizar(dispositivos);
		Assert.assertEquals(1, accion.getEjecuciones());
	}
	
}