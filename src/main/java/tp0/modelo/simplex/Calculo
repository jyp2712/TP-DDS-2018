
package tp0.modelo.simplex;

import java.util.ArrayList;

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
import tp0.modelo.dispositivo.TipoDispositivoEnum;

public class Calculo {
	
	
	double MAXIMO=612;
	ArrayList <Dispositivo>dispositivos;
	ArrayList <TipoDispositivoEnum> tipoDispositivos= new ArrayList<>(); 
	
	double [] resultados;
	double valorHogarOptimo;
	public Calculo(ArrayList <Dispositivo>dispositivos) {
		//dispositivos.removeIf(dispostivo->dispostivo.noEsOptimizable());   no estoy segura si incluir en el enum  de las restricciones eso
		this.dispositivos=dispositivos;
	for(int i=0;i<dispositivos.size();i++) {
		tipoDispositivos.add(dispositivos.get(i).getTipoDispositivoEnum());
	} 
	}
	
	private double[][] crearMatrizIdentidad(int n){
		double [][] matriz= new double [n][n];
		for(int i=0; i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j)
					matriz[i][j]=1;
				else
					matriz[i][j]=0;
			}
		}
		return matriz;
	}
	
	private double [] arrayUnos(int n) {
		  double [] array=new double[n];
		  for(int i=0;i<dispositivos.size();i++) {
			  array[i]=1;
			}
	return array;
	  }
	private double[] obtenerConsumokWh() {
		  double [] consumos= new double [dispositivos.size()];
		  int i=0;
		  for(TipoDispositivoEnum dispositivo :tipoDispositivos) {
			  consumos[i]=dispositivo.kwPorHora();
			  i++;
		  }
		  return consumos;
	  }
	private ArrayList <LinearConstraint> restricciones(){
		  ArrayList <LinearConstraint> restriccions= new ArrayList<LinearConstraint>(); 
		  double [][] identidad=new double [dispositivos.size()][dispositivos.size()];
		  identidad =this.crearMatrizIdentidad(dispositivos.size());
		  		for(int i=0;i<dispositivos.size();i++ ) {
	
		restriccions.add(new LinearConstraint(identidad[i], Relationship.LEQ, tipoDispositivos.get(i).getRestricciones().getMaximo()));
		restriccions.add(new LinearConstraint(identidad[i], Relationship.GEQ, tipoDispositivos.get(i).getRestricciones().getMinimo()));
		}
		
		  restriccions.add(new LinearConstraint(obtenerConsumokWh(),Relationship.LEQ,MAXIMO));
		  return restriccions;
	  }	
	
			public void resolver(){
					
		LinearObjectiveFunction funcion = new LinearObjectiveFunction(this.arrayUnos(dispositivos.size()), 0);
				
			PointValuePair resultado= 	 new SimplexSolver().optimize(
										 funcion,
										 new LinearConstraintSet(this.restricciones()),	
										 GoalType.MAXIMIZE, 
										 new NonNegativeConstraint(true),
										 new MaxIter(100));
		
		resultados=resultado.getPoint();
		valorHogarOptimo= funcion.value(resultados);
		for(int j=0;j<dispositivos.size();j++) {
			dispositivos.get(j).setUsoOptimo(resultados[j]);
				
		}
		
			}
		public double getValorHogarEficiente() {
			this.resolver();
			return valorHogarOptimo;
		}
	
			
}
