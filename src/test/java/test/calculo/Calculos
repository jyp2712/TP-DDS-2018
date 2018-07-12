package test.calculo;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp0.modelo.dispositivo.Dispositivo;
import tp0.modelo.dispositivo.DispositivoEstandar;
import tp0.modelo.dispositivo.DispositivoInteligente;
import tp0.modelo.dispositivo.TipoDispositivoEnum;
import tp0.modelo.simplex.Calculo;

public class Calculos {
	DispositivoInteligente aireAcondicionado= new DispositivoInteligente(TipoDispositivoEnum.AIRE_ACONDICIONADO_2200_FRIGORIAS);
	DispositivoInteligente lampara11= new DispositivoInteligente(TipoDispositivoEnum.LAMPARA_11_W);
	DispositivoInteligente led40= new DispositivoInteligente(TipoDispositivoEnum.TELEVISOR_LED_40);
	DispositivoInteligente compu= new DispositivoInteligente(TipoDispositivoEnum.PC_ESCRITORIO);
	DispositivoEstandar lavarropassemi= new DispositivoEstandar(TipoDispositivoEnum.LAVARROPAS_SEMI_AUTOMATICO_5_KG,100);
	DispositivoEstandar microondas= new DispositivoEstandar(TipoDispositivoEnum.MICROONDAS_CONVENCIONAL,100);
	DispositivoEstandar plancha= new DispositivoEstandar(TipoDispositivoEnum.PLANCHA_VAPOR,100);
	DispositivoInteligente ventiladorTecho= new DispositivoInteligente(TipoDispositivoEnum.VENTILADOR_TECHO);
	Calculo calculo;
	@Before
	public void setUp() throws Exception {
		ArrayList <Dispositivo> dispositivos=new ArrayList<>();
		dispositivos.add(aireAcondicionado);
		dispositivos.add(lampara11);
		dispositivos.add(led40);
		dispositivos.add(compu);
		dispositivos.add(lavarropassemi);
		dispositivos.add(microondas);
		dispositivos.add(plancha);
		dispositivos.add(ventiladorTecho);
		calculo=new Calculo(dispositivos);
	}
	
	@Test
	public void valorOptimo(){
		Assert.assertEquals(1875, calculo.getValorHogarEficiente(),0);
	}
	@Test
	public void planchaCantidadHorasOptima() {
		calculo.resolver();
		Assert.assertEquals(30, plancha.getUsoOptimo(),0);
	}
	
}
