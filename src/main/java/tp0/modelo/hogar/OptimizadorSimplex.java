package tp0.modelo.hogar;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import tp0.modelo.dispositivo.Dispositivo;

public class OptimizadorSimplex implements Optimizador {

	protected SimplexSolver simplex = new SimplexSolver();
	protected LinearObjectiveFunction funcion;
	protected List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
	protected RealVector coeficientes;
	protected RealVector indices;
	protected RealVector cantDispositivos;
	protected int posicion = 0;
	
	public OptimizadorSimplex() {}

	private void setCondiciones(List<Dispositivo> dispositivos) {
		coeficientes = new ArrayRealVector(dispositivos.size());
		indices = new ArrayRealVector(dispositivos.size());
		cantDispositivos = new ArrayRealVector(dispositivos.size());
		
		dispositivos.stream().forEach(disp -> this.setArrays(disp));
		restricciones.add(new LinearConstraint(cantDispositivos, Relationship.LEQ, 612));
		funcion = new LinearObjectiveFunction(coeficientes, 0);
	}

	private void setArrays(Dispositivo disp) {
		coeficientes.setEntry(posicion, disp.getCoeficiente());
		indices.setEntry(posicion, 1);
		restricciones.add(new LinearConstraint(indices.copy(), Relationship.GEQ, disp.getUsoMinimo()));
		restricciones.add(new LinearConstraint(indices.copy(), Relationship.LEQ, disp.getUsoMaximo()));
		indices.set(0);
		cantDispositivos.setEntry(posicion, 1);
		posicion++;
	}

	public double[] optimizar(List<Dispositivo> dispositivos) {
		setCondiciones(dispositivos);
		try {
			PointValuePair resultado = simplex.optimize(
					new MaxIter(100), funcion, new LinearConstraintSet(restricciones),
					GoalType.MAXIMIZE, new NonNegativeConstraint(true));
			return resultado.getFirst();
		}catch(Exception e) {
			throw new RuntimeErrorException(null, "No se puede optimizar");
		}
		
	}

	public SimplexSolver getSimplex() {
		return simplex;
	}

	public LinearObjectiveFunction getFuncion() {
		return funcion;
	}

	public List<LinearConstraint> getRestricciones() {
		return restricciones;
	}

	public RealVector getCoeficientes() {
		return coeficientes;
	}

	public RealVector getIndices() {
		return indices;
	}

	public RealVector getCantDispositivos() {
		return cantDispositivos;
	}
	
}
