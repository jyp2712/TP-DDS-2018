package tp0.modelo.hogar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

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
    			List<Dispositivo> disp = dispositivos.stream()
    					.filter(d -> d.optimizable()).collect(Collectors.toList());
    			disp.forEach(d -> imprimirResultadoOptimizador(resultado, d, disp.indexOf(d)));
    		}

			private void imprimirResultadoOptimizador(double[] resultado, Dispositivo disp, int i) {
				if(disp.optimizable()) {
					System.out.println("Dispositivo: " + disp.getNombreGenerico() +
							" - Resultado: " + resultado[i]);
				}
			}

    	};
        timer.scheduleAtFixedRate(timerTask, 0, horas.toStandardDuration().getMillis());	
    }

}
