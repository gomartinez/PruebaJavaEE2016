package capa_negocio;

import java.util.Vector;
import java.util.Hashtable;

public class Fachada {
	
	//Singleton Fachada
	private static Fachada fachada;
	private Fachada(){
		}
	public static Fachada getFachada(){
		 if (fachada==null) {
		 	 fachada=new Fachada();
		 }
		 return fachada;
	}
	

	Manager_Pelicula manager_pelicula = Manager_Pelicula.getManagerPelicula();
	Manager_Socio manager_socio = Manager_Socio.getManagerSocio();
	
	public Vector devolver_Vector_Peliculas() {
		return manager_pelicula.devolver_Vector_Peliculas();

	}
	public Vector devolver_Vector_Socios() {
		return manager_socio.devolver_Vector_Socios();
	}

}
