package tp0.modelo.dispositivo.regla;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import tp0.modelo.PersistentObject;

@Entity
public class Regla extends PersistentObject{

	@OneToOne
	private Condicion condicion;
	@OneToOne
	private Accion accion;

	public Regla(Condicion condicion, Accion accion) {
		this.setCondicion(condicion);
		this.setAccion(accion);
	}

	public Condicion getCondicion() {
		return condicion;
	}

	private void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Accion getAccion() {
		return accion;
	}

	private void setAccion(Accion accion) {
		this.accion = accion;
	}

	public void ejecutar(double resultadoConsumo) {
		if (this.getCondicion().cumplida(resultadoConsumo)) {
			this.getAccion().ejecutar();
		}
	}
}
