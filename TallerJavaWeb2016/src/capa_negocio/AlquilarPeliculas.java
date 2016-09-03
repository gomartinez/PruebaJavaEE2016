package capa_negocio;

public class AlquilarPeliculas {
	private Pelicula P;
	private Socio S;
	
	public AlquilarPeliculas (Socio soc, Pelicula pel){
		this.P=pel;
		this.S=soc;
	}
	
	public Pelicula getPelicula(){
		return P;
	}
	
	public void setPelicula(Pelicula P){
		this.P = P;
	}

	
	public Socio getSocio(){
		return S;
	}
	
	public void setSocio(Socio S){
		this.S = S;
	}
	
}
