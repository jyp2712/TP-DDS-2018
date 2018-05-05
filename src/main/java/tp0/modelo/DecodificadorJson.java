package tp0.modelo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import tp0.modelo.repositorios.fuentes.Fuente;

public class DecodificadorJson {

	File archivo;
	Class<?> tipo;
	ObjectMapper mapeador;

	public DecodificadorJson (Fuente<File> fuenteArchivo, Class<?> tipo) {
		this.archivo = fuenteArchivo.obtenerRecurso();
		this.tipo = tipo;
		this.mapeador = new ObjectMapper();
	}

	public List<?> leer() {
		try {
			Object[] arreglo = (Object[]) mapeador.readValue(archivo, arrayType(tipo));
			return Arrays.asList(arreglo);
		} catch(IOException e) {
			throw new RuntimeException(String.format("Error al intentar leer datos del archivo '%s' (%s)"
					, e.getMessage()));
		}
	} 

	private Class<?> arrayType(Class<?> type) {
		try {
			return Class.forName("[L" + type.getName() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
