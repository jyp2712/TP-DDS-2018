package tp0.modelo.hogar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.regla.Accion;
import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.dispositivo.regla.Regla;

public class Hogar {
	
	protected List<Regla> reglas = new ArrayList<>();
	protected boolean accionAutomatica = false;
	protected List<ResultadoOptimizador> resultados = new ArrayList<>();
	
	public Hogar() {}
	
	public double[] optimizar(List<Dispositivo> dispositivos) {
		Optimizador optimizador = new OptimizadorSimplex();
		List<Dispositivo> disp = dispositivos.stream()
				.filter(d -> d.optimizable()).collect(Collectors.toList());
		
		double[] resultado = optimizador.optimizar(disp);
		
		this.guardarResultados(disp, resultado);
		
		if(this.accionAutomatica) {
			this.reglas.stream().forEach(regla -> 
			accionar(regla.getAccion().getDispositivo(), resultado, regla, disp));
		}
		
		return resultado;
	}

	private void guardarResultados(List<Dispositivo> disp, double[] resultado) {
		disp.forEach(d -> resultados.add(new ResultadoOptimizador(d, resultado[disp.indexOf(d)])));
	}

	private void accionar(DispositivoInteligente dispositivoInteligente, double[] resultado,
			Regla regla, List<Dispositivo> disp) {
		int posicion = disp.indexOf(dispositivoInteligente);
		double resultadoConsumo = resultado[posicion];
		regla.ejecutar(resultadoConsumo);
	}

	public void configurarReglas(List<Accion> acciones) {
		acciones.stream().forEach(accion -> reglas.add(crearRegla(accion)));
	}

	private Regla crearRegla(Accion accion) {
		
		Condicion condicion = new Condicion(accion.getDispositivo().getSensor()) {
			
			@Override
			public boolean cumplida(double resultado) {
				return accion.getDispositivo().consumoTotal(new DateTime().dayOfMonth().withMinimumValue(), DateTime.now()) > resultado;
			}
		};
		
		return new Regla(condicion, accion);
	}
	
	public void activarAccionesAuto() {
		this.accionAutomatica = true;
	}
	
	public void desactivarAccionesAuto() {
		this.accionAutomatica = false;
	}

	public List<ResultadoOptimizador> getResultados() {
		return resultados;
	}

}
