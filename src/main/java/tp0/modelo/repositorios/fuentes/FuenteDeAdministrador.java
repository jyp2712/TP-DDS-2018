package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.Administrador;

public interface FuenteDeAdministrador {

	public List<Administrador> cargar();
	
	public void guardar(List<Administrador> administradores);
}
