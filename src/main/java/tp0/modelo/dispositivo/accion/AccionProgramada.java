package tp0.modelo.dispositivo.accion;

import tp0.modelo.dispositivo.condicion.Condicion;

public class AccionProgramada implements Accion {
	
	private Condicion condicion;
	private Accion accion;
	
	public AccionProgramada(Condicion condicion, Accion accion) {
		this.setCondicion(condicion);
		this.setAccion(accion);
	}

	private Condicion getCondicion() {
		return condicion;
	}

	private void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	private Accion getAccion() {
		return accion;
	}

	private void setAccion(Accion accion) {
		this.accion = accion;
	}

	@Override
	public void ejecutar() {
		this.getAccion().ejecutar();
	}

}
