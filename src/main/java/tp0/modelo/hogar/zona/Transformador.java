package tp0.modelo.hogar.zona;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import tp0.modelo.Cliente;
import tp0.modelo.PersistentObject;
import tp0.modelo.reportes.ReporteConsumoTransformador;

@Entity
public class Transformador extends PersistentObject{
	
	@OneToMany
	@JoinColumn(name="transformador_id")
	protected List<Cliente> clientes = new ArrayList<>();
	@Transient
	ReporteConsumoTransformador reporteConsumo;
	
	public Transformador() {}
	
	public void sacarCliente(Cliente _cliente) {
		clientes.remove(_cliente);
	}

	public void agregarCliente(Cliente _cliente) {
		clientes.add(_cliente);
	}
	
	public boolean pertenece(Cliente cliente) {
		return clientes.contains(cliente);
	}

	public double energiaSuministrada(DateTime fechaInicial, DateTime fechaFinal) {
		return clientes.stream().mapToDouble(cliente -> cliente.consumoTotal(fechaInicial, fechaFinal)).sum();
	}

	public long getId() {
		
		return this.id;
		
	}
	
	public void comenzarReporte() {
		this.reporteConsumo = new ReporteConsumoTransformador();
		this.reporteConsumo.comenzarReporte(this, DateTime.now().toString());
	}
	
	public void finalizarReporte() {
		this.reporteConsumo.finalizarReporte(DateTime.now().toString());
	}

}
