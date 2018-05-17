package tp0.modelo;

public class EstadoApagado implements Estado {

	@Override
	public boolean estaEncendido() {
		return false;
	}
	@Override
	public void apagarse(DispositivoInteligente dispositivo) {
		
	}
	
}
