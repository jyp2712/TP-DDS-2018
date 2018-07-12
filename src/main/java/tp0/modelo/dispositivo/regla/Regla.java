package tp0.modelo.dispositivo.regla;

public class Regla {

	private Condicion condicion;
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
