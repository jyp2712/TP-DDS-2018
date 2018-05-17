package tp0.modelo;

public interface TipoDispositivo {
	public boolean estaEncendido();
	public boolean estaApagado();
	public float energiaConsumida();
	public float consumoTotal();
	public void encenderse();
	public void apagarse();
	public void ahorrarEnergia();
	
}
