package capa_negocio;
import java.util.Scanner;


public class ReservaPeliculas {
	private Pelicula P;
	private Socio S;
	
	public ReservaPeliculas (Socio socio, Pelicula pelicula){
		this.P = pelicula;
		this.S = socio;
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
