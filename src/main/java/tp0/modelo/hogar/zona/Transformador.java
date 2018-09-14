package tp0.modelo.hogar.zona;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tp0.modelo.Cliente;
import tp0.modelo.PersistentObject;

@Entity
public class Transformador extends PersistentObject{
	
	protected int id_transformador;
	@OneToMany
	@JoinColumn(name="transformador_id")
	protected List<Cliente> clientes = new ArrayList<>();
	
	public Transformador() {}
	
	@JsonCreator
	public Transformador(@JsonProperty("id") int id, @JsonProperty("clientes") List<Cliente> clientes) {
		this.id_transformador = id;
		clientes.addAll(clientes);
	}

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

	public int getId() {
		return this.id_transformador;
	}
	
	public List<Cliente> getClientes(){
		return this.clientes;
	}

}
