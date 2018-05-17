package tp0.modelo;

public class DispositivoEstandar{
	private double KwXHora;
	private double horasDeUso;

	public DispositivoEstandar(double kwXHora, double horasDeUso) {
		this.KwXHora = kwXHora;
		this.horasDeUso = horasDeUso;
	}

	public double consumo() {
		return this.KwXHora * this.horasDeUso;
	}

	public Boolean esInteligente() {
		return false;
	}
	
	public DispositivoAdaptado adaptarAInteligente(String nombreGenerico, Boolean estado) {
		return new DispositivoAdaptado(nombreGenerico, this.KwXHora, estado);
	}

}
