package test.dispositivo;

import org.joda.time.DateTime;

import tp0.modelo.dispositivo.*;
import tp0.modelo.dispositivo.accion.AccionInmediata;


public class TostadoraMock implements DispositivoFisicoAdapter {

	@Override
	public double consumo(int horas) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double consumoTotal(DateTime periodo) {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public void apagar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ahorrarEnergia() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecutar(AccionInmediata accionInmediata) {
		// TODO Auto-generated method stub
		
	}
	
}