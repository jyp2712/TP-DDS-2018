package tp0.modelo.repositorios.fuentes;

import java.util.List;

import tp0.modelo.CodificadorJson;
import tp0.modelo.Administrador;

public class FuenteJsonDelAdministrador implements FuenteDeAdministrador{
	
	CodificadorJson codificador;
	
	public FuenteJsonDelAdministrador(String nombreDeArchivo){
		codificador = new CodificadorJson(nombreDeArchivo, Administrador.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Administrador> cargar() {
		
		return (List<Administrador>) codificador.leer();
	}

	@Override
	public void guardar(List<Administrador> administradores) {
		
		codificador.escribir(administradores);		
	}
	

}
