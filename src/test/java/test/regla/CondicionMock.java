package test.regla;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import tp0.modelo.dispositivo.regla.Condicion;
import tp0.modelo.dispositivo.regla.SensorAdapter;
@Entity
public class CondicionMock extends Condicion {

	@Id
	@GeneratedValue
	protected long id;
	
	public CondicionMock(SensorAdapter sensor) {
		super(sensor);
	}

	@Override
	public boolean cumplida(double resultado) {
		return true;
	}
}
