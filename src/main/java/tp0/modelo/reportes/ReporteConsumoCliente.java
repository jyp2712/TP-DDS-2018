package tp0.modelo.reportes;

import javax.persistence.Entity;

import org.joda.time.DateTime;

import tp0.modelo.Cliente;

@Entity
public class ReporteConsumoCliente extends ReporteConsumo{
	
	protected Integer dni;
	protected String nombreCliente;
	protected String apellidoCliente;
	protected double consumo;	

	public String getApellidoCliente() {
		return this.apellidoCliente;
	}
	
	public String getNombreCliente() {
		return this.nombreCliente;
	}
	
	public Integer getDNI() {
		return this.dni;
	}
	
	public double getConsumo() {
		return this.consumo;
	}
	
	public void setApellidoCliente(String apellido) {
		this.apellidoCliente = apellido;
	}
	
	public void setNombreCliente(String nombre) {
		this.nombreCliente = nombre;
	}
	
	public void setDNI(int dni) {
		this.dni = dni;
	}
	public ReporteConsumoCliente() {}
	
	public ReporteConsumoCliente(Cliente cliente, String fechaInicial, String fechaFinal) {
		this.dni = cliente.getDocumento();
		this.nombreCliente = cliente.getNombre();
		this.apellidoCliente = cliente.getApellido();
		this.consumo = cliente.consumoTotal(new DateTime(fechaInicial), new DateTime(fechaFinal));
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;		
	}
	
}