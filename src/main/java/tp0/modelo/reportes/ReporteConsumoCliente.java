package tp0.modelo.reportes;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.Cliente;

@Entity
public class ReporteConsumoCliente extends ReporteConsumo{
	
	protected Integer dni;
	protected String nombreCliente;
	protected String apellidoCliente;
	@Transient
	protected Cliente cliente;
	
	public String getApellidoCliente() {
		return this.apellidoCliente;
	}
	
	public String getNombreCliente() {
		return this.nombreCliente;
	}
	
	public Integer getDNI() {
		return this.dni;
	}
	
	public void comenzarReporte(Cliente cliente, String fechaInicial) {
		this.fechaInicio = fechaInicial;
		this.cliente = cliente;
		this.dni = cliente.getDocumento();
		this.nombreCliente = cliente.getNombre();
		this.apellidoCliente = cliente.getApellido();
	}
	
	public void finalizarReporte(String fechaFinal) {
		this.setFechaFin(fechaFinal);
		this.setConsumo(cliente.consumoTotal(new DateTime(fechaInicio), new DateTime(fechaFinal)));
	}
	
	
}