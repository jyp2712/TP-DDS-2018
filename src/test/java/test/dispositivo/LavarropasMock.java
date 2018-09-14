package test.dispositivo;
import java.util.Arrays;
import java.util.List;

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

	@Override
	public List<Intervalo> intervalosEncendido(DateTime fechaInicial, DateTime fechaFinal) {
		return Arrays.asList(new Intervalo("2018-09-01", "2018-09-02"), 
				new Intervalo("2018-09-05", "2018-09-20"));
	}
	
}