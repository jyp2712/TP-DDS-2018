package tp0.modelo.dispositivo.regla;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import tp0.modelo.PersistentObject;

@Entity
public class Regla extends PersistentObject{

	@Embedded
	private Condicion condicion;
	@OneToOne
	private Accion accion;

	public Regla() {};
	
	public Regla(Condicion condicion, Accion accion) {
		this.setCondicion(condicion);
		this.setAccion(accion);
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Accion getAccion() {
		return accion;
	}

	public void setAccion(Accion accion) {
		this.accion = accion;
	}

	public void ejecutar(double resultadoConsumo) {
		if (this.getCondicion().cumplida(resultadoConsumo)) {
			this.getAccion().ejecutar();
		}
	}
}
