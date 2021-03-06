import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp0.modelo.Administrador;

public class AdministradorTest {
	
	Administrador administrador;
	DateTime fechaActual;
	Months diezMesesDeAntiguedad;

	@Before
	public void setUp() throws Exception {
		fechaActual = DateTime.now();
		administrador = new Administrador(101010, "Juan", "Lopez", "Calle Falsa 123", fechaActual.minusMonths(10).toString());
		diezMesesDeAntiguedad = Months.months(10);
	}
	
	@Test
	public void testAntiguedadAdministrador() {
		Assert.assertEquals(diezMesesDeAntiguedad, administrador.antiguedadAdministrador());
	}
}