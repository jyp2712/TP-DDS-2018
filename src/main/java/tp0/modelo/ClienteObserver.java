package tp0.modelo;

public class ClienteObserver{

	protected Cliente cliente;
	
	public ClienteObserver(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void update() {
		cliente.getDomicilioServicio().actualizarDispositivos(cliente.getDispositivos());
	}

}
