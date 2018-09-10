package tp0.modelo.dispositivo.regla;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.PersistentObject;

@Entity
public abstract class SensorAdapter extends PersistentObject{
	public abstract double medicion(DateTime fechaInicial, DateTime fechaFinal);
}
