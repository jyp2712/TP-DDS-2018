package test.hogar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoConcreto;
import tp0.modelo.dispositivo.DispositivoConcretoEnum;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.hogar.OptimizadorSimplex;
import tp0.modelo.repositorios.RepositorioEnMemoria;

public class OptimizadorTest {

	OptimizadorSimplex optimizador;
	RepositorioEnMemoria<DispositivoConcreto> repositorioDeDispositivos = new RepositorioEnMemoria<DispositivoConcreto>();
	DispositivoInteligente lavarropas;
	DispositivoEstandar televisor;
	List<Dispositivo> dispositivos = new ArrayList<>();
	double[] resultado;

	@Before
	public void setUp() throws Exception {
	
		repositorioDeDispositivos.agregar(
				Arrays.asList(new DispositivoConcreto(DispositivoConcretoEnum.HELADERA_CONFREEZER, 0.09, 0, 0),
						new DispositivoConcreto(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG, 0.175, 6, 30),
				new DispositivoConcreto(DispositivoConcretoEnum.TELEVISOR_TUBO_21, 0.075, 90, 360),
				new DispositivoConcreto(DispositivoConcretoEnum.VENTILADOR_PIE, 0.09, 120, 360)));
		
		lavarropas = new DispositivoInteligente(DispositivoConcretoEnum.LAVARROPAS_AUTO_5KG.toString(), 150);
		lavarropas.setDispositivoGenerico(repositorioDeDispositivos);
		
		televisor = new DispositivoEstandar(DispositivoConcretoEnum.TELEVISOR_TUBO_21.toString(), 100, 10);
		televisor.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivos.addAll(Arrays.asList(lavarropas, televisor));
		optimizador = new OptimizadorSimplex();
		optimizador.setCondiciones(dispositivos);
		resultado = optimizador.optimizar();
	}

	@Test
	public void testDimensionRealVectors() {
		Assert.assertTrue(optimizador.getCoeficientes().getDimension() == DispositivoConcretoEnum.values().length);
		Assert.assertTrue(optimizador.getCantDispositivos().getDimension() == DispositivoConcretoEnum.values().length);
		Assert.assertTrue(optimizador.getIndices().getDimension() == DispositivoConcretoEnum.values().length);
	}
	
	@Test
	public void testCoeficientes() {
		Assert.assertTrue(optimizador.getCoeficientes().getMaxValue() == lavarropas.getCoeficiente());
		Assert.assertTrue(optimizador.getCoeficientes().getMinValue() == 0);
	}

	@Test
	public void testResultado() {
		Assert.assertTrue(resultado[lavarropas.getNombreGenericoPosicion()] == lavarropas.getUsoMaximo());
		Assert.assertTrue(resultado[televisor.getNombreGenericoPosicion()] == televisor.getUsoMaximo());
	}

	
}