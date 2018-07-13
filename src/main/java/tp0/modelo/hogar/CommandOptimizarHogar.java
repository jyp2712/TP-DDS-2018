package tp0.modelo.hogar;

import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.Hours;

public class CommandOptimizarHogar {
	
    public CommandOptimizarHogar(Hogar hogar, Hours horas) {
    	Timer timer = new Timer(false);
    	TimerTask timerTask = new TimerTask() {
    		
    		@Override
    		public void run() {
    			hogar.optimizar();
    		}

    	};
        timer.scheduleAtFixedRate(timerTask, 0, horas.getHours());	
    }

}
