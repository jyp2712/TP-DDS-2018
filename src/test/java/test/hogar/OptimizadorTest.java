package test.hogar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoConcreto;
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
				Arrays.asList(new DispositivoConcreto("HELADERA_CONFREEZER", 0.09, 0, 0, false),
						new DispositivoConcreto("LAVARROPAS_AUTO_5KG", 0.175, 6, 30, true),
				new DispositivoConcreto("TELEVISOR_TUBO_21", 0.075, 90, 360, true),
				new DispositivoConcreto("VENTILADOR_PIE", 0.09, 120, 360, true)));
		
		lavarropas = new DispositivoInteligente("LAVARROPAS_AUTO_5KG", 150);
		lavarropas.setDispositivoGenerico(repositorioDeDispositivos);
		
		televisor = new DispositivoEstandar("TELEVISOR_TUBO_21", 100, 10);
		televisor.setDispositivoGenerico(repositorioDeDispositivos);
		
		dispositivos.addAll(Arrays.asList(lavarropas, televisor));
		optimizador = new OptimizadorSimplex();
		resultado = optimizador.optimizar(dispositivos);
	}

	@Test
	public void testDimensionRealVectors() {
		Assert.assertEquals(dispositivos.size(), optimizador.getCoeficientes().getDimension(), 0);
		Assert.assertEquals(dispositivos.size(), optimizador.getCantDispositivos().getDimension(), 0);
		Assert.assertEquals(dispositivos.size(), optimizador.getIndices().getDimension(), 0);
	}
	
	@Test
	public void testCoeficientes() {
		Assert.assertEquals(lavarropas.getCoeficiente(), optimizador.getCoeficientes().getMaxValue(), 0);
		Assert.assertEquals(0.075, optimizador.getCoeficientes().getMinValue(), 0);
	}

	@Test
	public void testResultado() {
		int posLavarropas = dispositivos.indexOf(lavarropas);
		int posTele = dispositivos.indexOf(televisor);
		Assert.assertEquals(lavarropas.getUsoMaximo(), resultado[posLavarropas], 0);
		Assert.assertEquals(televisor.getUsoMaximo(), resultado[posTele], 0);
	}

	
}