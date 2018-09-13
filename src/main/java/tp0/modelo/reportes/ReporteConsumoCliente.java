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
	
	public ReporteConsumoCliente(Cliente cliente, String fechaInicial) {
		
		super(fechaInicial);
		this.dni = cliente.getDocumento();
		this.nombreCliente = cliente.getNombre();
		this.apellidoCliente = cliente.getApellido();
		this.cliente = cliente;
		
		
	}
	
	public String getApellidoCliente() {
		return this.apellidoCliente;
	}
	
	public String getNombreCliente() {
		return this.nombreCliente;
	}
	
	public Integer getDNI() {
		return this.dni;
	}
	
	public void finalizarReporte(DateTime fechaFinal) {
		this.setFechaFin(fechaFinal.toString());
		this.setConsumo(cliente.consumoTotal(new DateTime(fechaInicio), fechaFinal));
	}
	
	
}