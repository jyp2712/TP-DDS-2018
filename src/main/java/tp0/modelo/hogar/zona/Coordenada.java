package tp0.modelo.hogar.zona;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenada {
	protected double longitud;
	protected double latitud;
	
	public Coordenada(double _long, double _lat) {
		longitud = _long;
		latitud = _lat;
	}
	
}
