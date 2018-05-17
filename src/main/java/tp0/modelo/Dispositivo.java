package tp0.modelo;

public class Dispositivo {
	private TipoDispositivo TipoDispositivo;
	
	public boolean estaEncendido() {
		return TipoDispositivo.estaEncendido();
	}
	
	public boolean estaApagado() {
		return TipoDispositivo.estaApagado();
	}
	
}
