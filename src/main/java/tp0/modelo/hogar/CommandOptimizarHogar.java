package tp0.modelo.hogar;

import java.util.Timer;
import java.util.TimerTask;

import org.joda.time.Hours;

public class CommandOptimizarHogar {
	
    public CommandOptimizarHogar(Hogar hogar, Hours horas) {
    	Timer timer = new Timer(true);
    	TimerTask timerTask = new TimerTask() {
    		
    		@Override
    		public void run() {
    			hogar.optimizar();
    			completeTask();			
    		}

    		private void completeTask() {
    	        try {
    	            Thread.sleep(10000);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }				
    		}
    	};
        timer.scheduleAtFixedRate(timerTask, 0, horas.getHours());	
    }

    public void ejecutar() {
    	try {
    		Thread.sleep(120000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}
