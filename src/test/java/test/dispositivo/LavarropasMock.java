package test.dispositivo;
import org.joda.time.DateTime;

import tp0.modelo.dispositivo.*;
import tp0.modelo.dispositivo.regla.Accion;

public class LavarropasMock implements DispositivoFisicoAdapter {

	@Override
	public double consumoUltimas(int horas) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double consumoTotal(DateTime fechaInicial, DateTime fechaFinal) {
		// TODO Auto-generated method stub
		return 1000;
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
	public void ejecutar(Accion accionInmediata) {
		// TODO Auto-generated method stub
		
	}
	
}