package tp0.modelo.hogar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.Minutes;

import tp0.modelo.dispositivo.Dispositivo;

public class CommandOptimizarHogar {
	
    public CommandOptimizarHogar(Hogar hogar, List<Dispositivo> dispositivos, Minutes horas) {
    	Timer timer = new Timer(false);
    	TimerTask timerTask = new TimerTask() {
    		
    		@Override
    		public void run() {
    			double[] resultado = hogar.optimizar(dispositivos);
    			System.out.println("Job Simplex ejecutado");
    			dispositivos.forEach(disp -> System.out.println("Dispositivo: " + disp.getNombreGenerico() +
    					" Consumo: " + resultado[dispositivos.indexOf(disp)]));
    		}

    	};
        timer.scheduleAtFixedRate(timerTask, 0, horas.toStandardDuration().getMillis());	
    }

}
