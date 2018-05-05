import java.io.File;
import org.junit.*;

import tp0.modelo.repositorios.fuentes.Fuente;
import tp0.modelo.repositorios.fuentes.FuenteArchivo;
	
public class FuenteTest {

	Fuente<File> fuente = new FuenteArchivo("administradores.json");

	@Test
	public void existeFuente() {
		Assert.assertTrue(fuente.obtenerRecurso().exists());
	}
	
	@Test
	public void fuenteNombre() {
		Assert.assertTrue(fuente.obtenerRecurso().getName().equals("administradores.json"));
	}

	@Test
	public void fuenteEsArchivo() {
		Assert.assertTrue(fuente.obtenerRecurso().isFile());
	}
	
}
