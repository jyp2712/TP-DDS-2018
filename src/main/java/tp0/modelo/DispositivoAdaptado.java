package tp0.modelo;

public class DispositivoAdaptado extends DispositivoInteligente{

	public DispositivoAdaptado(String nombreGenerico, double KwXHora, Boolean estado) {
		super(nombreGenerico, KwXHora);
	}
	
	public int otorgarPuntos(){
		return 10;
	}
	
	public DispositivoEstandar desadaptar(double horasDeUso) {
		return new DispositivoEstandar(this.KwXHora, horasDeUso);
	}
	
}
