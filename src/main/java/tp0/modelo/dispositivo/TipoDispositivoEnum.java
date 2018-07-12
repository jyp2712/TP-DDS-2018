package tp0.modelo.dispositivo;

public enum TipoDispositivoEnum {
	
	AIRE_ACONDICIONADO_3500_FRIGORIAS {
		@Override
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return false; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 1.613;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.AIRE_ACONDICIONADO;
		}  
	},
	AIRE_ACONDICIONADO_2200_FRIGORIAS {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return true; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 1.013;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.AIRE_ACONDICIONADO;
		}
	},
	TELEVISOR_COLOR_TUBO_21 {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return false; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.075;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	TELEVISOR_COLOR_TUBO_29_A_34 {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return false; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.175;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	TELEVISOR_LCD_40 {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return false; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.18;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	TELEVISOR_LED_24 {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return true; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.04;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	TELEVISOR_LED_32 {
		public boolean esBajoConsumo() { 
			// TODO Auto-generated method stub
			return true; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.055;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	TELEVISOR_LED_40 {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.08;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.TELEVISOR;
		}
	},
	HELADERA_CON_FREEZER {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.09;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			// tendriamos un par de opciones acá
			// devolver null, igual las heladeras no forman parte del calculo del simplex
			// crear un tipo de restriccion que devuelva max integer para maximo horas y 0 para minimo horas
			// me parece mejor la primera porque cuando matcheemos los dispositivos con el simplex vamos a tener 
			// que preguntar si es heladera para esquivarlo
			return null;
		}
	},
	HELADERA_SIN_FREEZER {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.075;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			// tendriamos un par de opciones acá
			// devolver null, igual las heladeras no forman parte del calculo del simplex
			// crear un tipo de restriccion que devuelva max integer para maximo horas y 0 para minimo horas
			// me parece mejor la primera porque cuando matcheemos los dispositivos con el simplex vamos a tener 
			// que preguntar si es heladera para esquivarlo
			return null;
		}
	},
	LAVARROPAS_AUTOMATICO_5_KG_CALENTAMIENTO_AGUA {
		public boolean esBajoConsumo() { return false; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.875;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAVARROPAS;
		}
	},
	LAVARROPAS_AUTOMATICO_5_KG {
		public boolean esBajoConsumo() { return true; }
		
		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.175;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAVARROPAS;
		}
	},
	LAVARROPAS_SEMI_AUTOMATICO_5_KG {
		public boolean esBajoConsumo() { return true; }
		
		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.1275;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAVARROPAS;
		}
	},
	VENTILADOR_PIE {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.09;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.VENTILADOR;
		}
	},
	VENTILADOR_TECHO {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.06;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.VENTILADOR;
		}
	},
	LAMPARA_HALOGENA_40_W {
		public boolean esBajoConsumo() { return false; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.04;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	LAMPARA_HALOGENA_60_W {
		public boolean esBajoConsumo() { return false; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.06;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	LAMPARA_HALOGENA_100_W {
		public boolean esBajoConsumo() { return false; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.015;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	LAMPARA_11_W {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.011;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	LAMPARA_15_W {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.015;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	LAMPARA_20_W {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.02;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.LAMPARA;
		}
	},
	PC_ESCRITORIO {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.4;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.COMPUTADORA;
		}
	},
	MICROONDAS_CONVENCIONAL {
		public boolean esBajoConsumo() { return true; }

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.64;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.MICROONDAS;
		}
	},
	PLANCHA_VAPOR {
		public boolean esBajoConsumo() { 
			return true; 
		}

		@Override
		public double consumo() {
			// TODO Auto-generated method stub
			return 0.75;
		}

		@Override
		public RestriccionEnum restricciones() {
			// TODO Auto-generated method stub
			return RestriccionEnum.PLANCHA;
		}
	};
	public abstract boolean esBajoConsumo();
	public abstract double consumo();
	public abstract RestriccionEnum restricciones();
	
}
