package tp0.modelo;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DispositivoInteligente implements Dispositivo{

	@JsonProperty
	protected String nombreGenerico;

	@JsonProperty
	protected double KwXHora;

	private Estado estado;
	
	private DispositivoFisicoAdapter dispositivoFisico;

	@JsonCreator
	public DispositivoInteligente(@JsonProperty("nombre generico") String nombreGenerico, 
			@JsonProperty("KW/H") double KwXHora) {
		setNombreGenerico(nombreGenerico);
		setKwXHora(KwXHora);
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	private void setNombreGenerico(String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}

	public double getKwXHora() {
		return KwXHora;
	}

	private void setKwXHora(double kwXHora) {
		this.KwXHora = kwXHora;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Boolean estaEncendido() {
		return this.getEstado().estaEncendido();
	}


	public Boolean estaApagado() {
		return !this.getEstado().estaEncendido();
	}
	
	public void apagarse() {
		this.getEstado().apagarse(this);
	}

	public void encenderse() {
		this.getEstado().encenderse(this);
	}
	
	public void modoAhorroEnergia() {
		this.getEstado().modoAhorroEnergia(this);
	}
	
	private DispositivoFisicoAdapter getDispositivoFisico() {
		return this.dispositivoFisico;
	}
	
	public void setDispositivoFisico(DispositivoFisicoAdapter dispositivoFisico) {
		this.dispositivoFisico = dispositivoFisico;
	}
	
/*	public double consumo() {
		return this.KwXHora;
	}*/

	/*public Boolean esInteligente() {
		return true;
	}*/
	
	public double consumo(Hours horas) {
		return this.getDispositivoFisico().consumo(horas);
	}
	
	public double consumoTotal(DateTime periodo) {
		return this.getDispositivoFisico().consumoTotal(periodo);
		// * Hours.hoursBetween(DateTime.now(), periodo).getHours()
	}
	
//	public int otorgarPuntos() {
//		return 15;
//	}

}
