package tp0.modelo.dispositivo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.reportes.Reporte;
import tp0.modelo.repositorios.RepositorioEnMemoria;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_dispositivo")
public abstract class Dispositivo{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String nombreGenerico;
	@ManyToOne(optional=false)
	protected DispositivoConcreto dispositivoConcreto;
	protected double kWXHora;
	@OneToMany(mappedBy = "dispositivo",cascade = CascadeType.ALL, orphanRemoval = false)
	protected List<Reporte> reportes = new ArrayList<Reporte>();
	@Transient
	protected Reporte reporte;

	public abstract double consumoUltimas(int horas);
	public abstract double consumoTotal(DateTime fechaInicial, DateTime fechaFinal);
	
	public DispositivoConcreto getDispositivoConcreto() {
		return this.dispositivoConcreto;
	}

	public void setDispositivoGenerico(RepositorioEnMemoria<DispositivoConcreto> dispositivos) {
		this.dispositivoConcreto = dispositivos.encontrar(disp -> this.getNombreGenerico().equals(disp.getNombreGenerico().toString()));
	}

	public double getKwXHora() {
		return this.kWXHora;
	}

	public void setKwXHora(double kwXHora) {
		this.kWXHora = kwXHora;
	}
	
	public double getCoeficiente() {
		return this.getDispositivoConcreto().getCoeficiente();
	}

	public double getUsoMinimo() {
		return this.getDispositivoConcreto().getUsoMinimo();
	}

	public double getUsoMaximo() {
		return this.getDispositivoConcreto().getUsoMaximo();
	}

	public String getNombreGenerico() {
		return this.nombreGenerico;
	}
	
	public boolean optimizable() {
		return this.getDispositivoConcreto().optimizable;
	}
	
	public void comenzarReporte() {
		this.reporte.setFechaInicioPeriodo(DateTime.now().toString());;
	}
	
	public void finalizarReporte(DateTime fechaFinPeriodo) {
		this.reporte.setFechaFinPeriodo(fechaFinPeriodo.toString());
		this.reporte.setConsumoPeriodo(this.consumoTotal(new DateTime(this.reporte.getFechaInicioPeriodo()), fechaFinPeriodo));
		this.reportes.add(reporte);
	}
	
	public Reporte getReporte() {
		return this.reporte;
	}
}
