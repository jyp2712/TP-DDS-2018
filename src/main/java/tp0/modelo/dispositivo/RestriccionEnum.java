package tp0.modelo.dispositivo;

public enum RestriccionEnum {
	
	AIRE_ACONDICIONADO {

		@Override
		public int usoMensualMinimo() {
			return 90;
		}

		@Override
		public int usoMensualMaximo() {
			return 360;
		}
		
	},
	LAMPARA {

		@Override
		public int usoMensualMinimo() {
			return 90;
		}

		@Override
		public int usoMensualMaximo() {
			return 360;
		}
		
	},
	TELEVISOR {

		@Override
		public int usoMensualMinimo() {
			return 90;
		}

		@Override
		public int usoMensualMaximo() {
			return 360;
		}
		
	},
	LAVARROPAS {

		@Override
		public int usoMensualMinimo() {
			return 6;
		}

		@Override
		public int usoMensualMaximo() {
			return 30;
		}
		
	},
	COMPUTADORA {

		@Override
		public int usoMensualMinimo() {
			return 60;
		}

		@Override
		public int usoMensualMaximo() {
			return 360;
		}
		
	},
	MICROONDAS {

		@Override
		public int usoMensualMinimo() {
			return 3;
		}

		@Override
		public int usoMensualMaximo() {
			return 15;
		}
		
	},
	PLANCHA {

		@Override
		public int usoMensualMinimo() {
			return 3;
		}

		@Override
		public int usoMensualMaximo() {
			return 30;
		}
		
	},
	VENTILADOR {

		@Override
		public int usoMensualMinimo() {
			return 120;
		}

		@Override
		public int usoMensualMaximo() {
			return 360;
		}
		
	};
	
	public abstract int usoMensualMinimo();
	public abstract int usoMensualMaximo();
}
