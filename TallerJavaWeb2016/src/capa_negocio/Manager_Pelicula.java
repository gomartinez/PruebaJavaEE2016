package capa_negocio;


import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Arrays;
import java.util.Hashtable;


import capa_persistencia.Manager_persistencia;

public class Manager_Pelicula {
	
	//Singleton Manager_Pelicula
	private static Manager_Pelicula manager_pelicula;
	private Manager_Pelicula(){
		}
	public static Manager_Pelicula getManagerPelicula(){
		 if (manager_pelicula==null) {
		 	 manager_pelicula=new Manager_Pelicula();
		 }
		 return manager_pelicula;
	}
	
	

	
	public Vector devolver_Vector_Peliculas(){
		
		//Hashtable no admite ser ordenado, para que las peliculas salgan ordenadas por Titulo
		//creo un vector que contenga las peliculas, lo ordeno y lo imprimo
		Vector coleccion_peliculas_vector = new Vector();
		Hashtable coleccion_peliculas = manager_pelicula.getColeccionPeliculas();
		for (Enumeration e = coleccion_peliculas.elements(); e.hasMoreElements();){
			
			Pelicula element = (Pelicula) e.nextElement();
			coleccion_peliculas_vector.add(element);
//			System.out.println("Pelicula encontrada: " + element.getTitulo());
		}
		Collections.sort(coleccion_peliculas_vector);
		return coleccion_peliculas_vector;

	}

	public Hashtable getColeccionPeliculas() {
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.getColeccionPeliculas();
	}

	
	
	public String[] alta_Pelicula (String tit, String act, String gen){
		String[] resultado = new String[2];
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		Hashtable coleccion_peliculas = manager_pelicula.getColeccionPeliculas();
		String codigo;
		int codigoInt;
		String[] array_actores;
		Pelicula nueva_pelicula = new Pelicula ();
		// Primera letra del titulo en mayuscula todas las otras en minuscula
		tit =  Character.toUpperCase(tit.charAt(0)) + tit.substring(1).toLowerCase();
		nueva_pelicula.setTitulo(tit);
		nueva_pelicula.setGenero(gen);
		
		act=act.toLowerCase();
		Vector <String> vector_actores;
		vector_actores=new Vector();
		array_actores = act.split(",");
	    for(int i = 0; i < array_actores.length; ++i) {
	        vector_actores.add(array_actores[i].trim());
	    }
	    nueva_pelicula.setActores(vector_actores);
	    
	    
		
		codigoInt= coleccion_peliculas.size();
		codigo = "O" + Integer.toString(++codigoInt);
		nueva_pelicula.setCodigoPelicula(codigo);
		
		try{
			manager_persistencia.alta_Pelicula_BD(nueva_pelicula, act);
			coleccion_peliculas.put(codigo, nueva_pelicula);
			resultado[0] = codigo;
			resultado[1] = "Alta Realizada";
			return resultado;
		}
		catch (Exception e) {
			resultado[1] = "Se produjo un error";
			return resultado;
		}
	}
	
	public String[] alta_Copia (String tit, String act, String gen, String est){
		String[] resultado = new String[2];
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		Hashtable coleccion_peliculas = manager_pelicula.getColeccionPeliculas();
		String codigo;
		int codigoInt;
		String[] array_actores;
	
		
		// asignacion Polimorfica
		Pelicula nueva_copia = new Copia();
		// Primera letra del titulo en mayuscula todas las otras en minuscula
		tit =  Character.toUpperCase(tit.charAt(0)) + tit.substring(1).toLowerCase();
		nueva_copia.setTitulo(tit);
		nueva_copia.setGenero(gen);
		nueva_copia.setEstado(est);
		Vector <String> vector_actores;
		vector_actores=new Vector();
		act=act.toLowerCase();
		array_actores = act.split(",");
	    
	    for(int i = 0; i < array_actores.length; ++i) {
	        vector_actores.add(array_actores[i].trim());
	    }
	    nueva_copia.setActores(vector_actores);
	    
		codigoInt= coleccion_peliculas.size();
		codigo = "C" + Integer.toString(++codigoInt);
		nueva_copia.setCodigoPelicula(codigo);
		
		try{
			manager_persistencia.alta_Pelicula_BD(nueva_copia, act);
			coleccion_peliculas.put(codigo, nueva_copia);
			resultado[0] = codigo;
			resultado[1] = "Alta Realizada";
			return resultado;
		}
		catch (Exception e) {
			resultado[1] = "Se produjo un error";
			return resultado;
		}
	}
	
	
	public void listar_Peliculas(Hashtable coleccion_peliculas){
		
		System.out.println("\nListado de Peliculas (ordenadas por titulo) \n");
		
		//Hashtable no admite ser ordenado, para que las peliculas salgan ordenadas por Titulo
		//creo un vector que contenga las peliculas, lo ordeno y lo imprimo
		Vector coleccion_peliculas_vector = new Vector();
		for (Enumeration e = coleccion_peliculas.elements(); e.hasMoreElements();){
			Pelicula element = (Pelicula) e.nextElement();
			coleccion_peliculas_vector.add(element);
		}
		Collections.sort(coleccion_peliculas_vector);
		for (Enumeration e = coleccion_peliculas_vector.elements(); e.hasMoreElements();){
			Pelicula element = (Pelicula) e.nextElement();
			element.imprimir();
		}
	}
		
	public Pelicula getPelicula(String codigo){
		Manager_Pelicula manager_peliculas = Manager_Pelicula.getManagerPelicula();
		Hashtable coleccion_peliculas = manager_peliculas.getColeccionPeliculas();
		Pelicula pelicula = (Pelicula) coleccion_peliculas.get(codigo);
		return pelicula;
	}

	public void listar_Peliculas_Alquiladas(Manager_persistencia manager_persistencia, Hashtable coleccion_peliculas, Hashtable coleccion_socios) {
		String titulo;
		String codigo;
		int nro_socio;
		String nombre;
		DatatypeAlquileres elem;
		Copia elemCopia;
		Vector lista_peliculas_alquiladas = new Vector();
		lista_peliculas_alquiladas = manager_persistencia.getColeccionAlquileres();

	
		
		System.out.println("\nListado de Alquileres\n");
		int cantidad_pelis_alquiladas = lista_peliculas_alquiladas.size();
		if (cantidad_pelis_alquiladas!=0){
			
			for (int i=0;i<cantidad_pelis_alquiladas;i++){
				elem =  (DatatypeAlquileres) lista_peliculas_alquiladas.get(i);
				nro_socio = elem.getSocio();
				codigo = elem.getCodigo();
				Socio socio = (Socio) coleccion_socios.get(nro_socio);
				nombre = socio.getNombre();
				Pelicula pelicula = (Pelicula) coleccion_peliculas.get(codigo);
				titulo = pelicula.getTitulo();
				System.out.print("Socio: " + nro_socio + "\t" + "Nombre: " + nombre );
				System.out.println("\t" + "Codigo: " + codigo + "\t" + "Titulo: " + titulo);
			
			}

		}
		else {
			System.out.println("No hay peliculas alquiladas");
			System.out.println("");

		}
		
		
	}
	

	
	public void listar_Peliculas_Reservadas(Manager_persistencia manager_persistencia, Hashtable coleccion_peliculas, Hashtable coleccion_socios) {
		String titulo;
		String codigo;
		int nro_socio;
		String nombre;
		DatatypeReservas elem;
		Copia elemCopia;
		Hashtable lista_peliculas_reservadas = new Hashtable();
		lista_peliculas_reservadas = manager_persistencia.getColeccionReservas();

	
		
		System.out.println("\nListado de Reservas\n");
		int cantidad_pelis_reservadas = lista_peliculas_reservadas.size();
		if (cantidad_pelis_reservadas!=0){
			
			for(Enumeration e=lista_peliculas_reservadas.elements();e.hasMoreElements();){
				elem =  (DatatypeReservas) e.nextElement();
				nro_socio = elem.getSocio();
				codigo = elem.getCodigo();
				Socio socio = (Socio) coleccion_socios.get(nro_socio);
				nombre = socio.getNombre();
				Pelicula pelicula = (Pelicula) coleccion_peliculas.get(codigo);
				titulo = pelicula.getTitulo();
				System.out.print("Socio: " + nro_socio + "\t" + "Nombre: " + nombre );
				System.out.println("\t" + "Codigo: " + codigo + "\t" + "Titulo: " + titulo);
			
			}

		}
		else {
			System.out.println("No hay peliculas reservadas");
			System.out.println("");

		}
		
		
	}
	

	
	
	
	
	
	
	
	
}
