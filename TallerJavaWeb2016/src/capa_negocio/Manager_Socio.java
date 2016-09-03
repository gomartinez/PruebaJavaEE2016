package capa_negocio;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.jws.WebService;

import java.util.Hashtable;



import capa_persistencia.Manager_persistencia;


public class Manager_Socio {
	Hashtable coleccion_socios;


//Singleton Manager_Socio
	private static Manager_Socio manager_socio;
	private Manager_Socio(){
		}
	public static Manager_Socio getManagerSocio(){
		 if (manager_socio==null) {
		 	 manager_socio=new Manager_Socio();
		 }
		 return manager_socio;
	}
	
	public DatatypeAltaSocio  alta_Socio (String nombre, String direccion){
		DatatypeAltaSocio respuesta = new DatatypeAltaSocio(0,"");
		int nro_socio;
		Manager_persistencia manager_persistencia= Manager_persistencia.getManagerPersistencia();
		Hashtable coleccion_socios = manager_socio.getColeccionSocios();
		Socio nuevo_socio = new Socio (nombre, direccion);
		nro_socio = coleccion_socios.size()+1;
		nuevo_socio.setNroSocio(nro_socio);

		
		try{
			manager_persistencia.alta_Socio_BD(nuevo_socio);
			coleccion_socios.put(nro_socio, nuevo_socio);
			respuesta.setSocio(nro_socio);
			respuesta.setMensaje("Alta de Socio realizada");
			return respuesta;
			
		}
		catch (Exception e) {
			respuesta.setMensaje("Se produjo un error");
			return respuesta;
		}
		
		
		
		
	}
	public void listar_Socios(Hashtable coleccion_socios){
		capa_negocio.Socio Socio = new capa_negocio.Socio();
		System.out.println("\nListado de Socios\n");
		for (Enumeration e = coleccion_socios.elements(); e.hasMoreElements();){
			Socio elem = (Socio) e.nextElement();
			
			System.out.print("Nro Socio: " + elem.getNroSocio() + "\t");
			System.out.print("Nombre: " + elem.getNombre() + "\t");
			if (elem.getNombre().length()<16){
				System.out.print("\t");
			}
			if (elem.getNombre().length()<8){
				System.out.print("\t");
			}
			System.out.println("Direccion: " + elem.getDireccion());

		}
		System.out.println("");
	}
	
	public String alta_Reserva(int nro_socio, String codigo){
		
		String respuesta = null;
		Manager_Pelicula manager_peliculas = Manager_Pelicula.getManagerPelicula();
		Hashtable  coleccion_peliculas =  manager_peliculas.getColeccionPeliculas();
		Hashtable coleccion_socios = manager_socio.getColeccionSocios();
		Socio socio = (Socio) coleccion_socios.get(nro_socio);
		Pelicula pelicula = (Pelicula) manager_peliculas.getPelicula(codigo);
		
		try {
			Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
			manager_persistencia.alta_Reserva_BD(nro_socio, codigo);
			ReservaPeliculas reserva = new ReservaPeliculas(socio,pelicula);
			Hashtable coleccion_reservas = manager_socio.getColeccionReservas();
			coleccion_reservas.put(codigo, reserva);
			respuesta = "Reserva Realizada";
		} catch (SQLException e) {
			//e.printStackTrace();
			respuesta = "Se produjo un error al dar de alta la reserva";
		}
		finally {
			return respuesta;
		}

		
		
	}
	
	public Socio getSocio(Hashtable coleccion_socios, int nro_soc){
		Socio socio = (Socio) coleccion_socios.get(nro_soc);
		return socio;
	}
	public String alta_Alquiler(int nro_soc, String codigo) {
		
		String respuesta = "";
		int nro_socio = nro_soc;
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		Manager_Pelicula manager_peliculas = Manager_Pelicula.getManagerPelicula();
		Vector coleccion_alquileres = manager_socio.getColeccionAlquileres();
		Hashtable coleccion_socios = manager_socio.getColeccionSocios();
		Hashtable coleccion_reservas = manager_socio.getColeccionReservas();
		Socio socio = (Socio) coleccion_socios.get(nro_socio);
		Pelicula pelicula = manager_peliculas.getPelicula(codigo);
		
		if (coleccion_reservas == null){
			try {
				manager_persistencia.alta_Alquiler_BD(nro_socio, codigo);
				AlquilarPeliculas alquiler = new AlquilarPeliculas(socio,pelicula);
				coleccion_alquileres.add(alquiler);
				respuesta = "Alquiler Realizado";
			} catch (SQLException e) {
				respuesta = "Se produjo un error SQL";	
				e.printStackTrace();
			} catch (Exception e) {
				respuesta = "Se produjo un error en la BD";				
				e.printStackTrace();
			}
			
		}
		else {
			if (coleccion_reservas.containsKey(codigo)){
				System.out.println("La pelicula esta reservada, se verificara.....");
				DatatypeReservas reserva_peliculas = (DatatypeReservas) coleccion_reservas.get(codigo);
				int nro_socio_que_reservo = reserva_peliculas.getSocio();
				if (nro_socio_que_reservo == nro_socio){
					try {
						manager_persistencia.alta_Alquiler_BD(nro_socio, codigo);
						AlquilarPeliculas alquiler = new AlquilarPeliculas(socio,pelicula);
						manager_persistencia.baja_Reserva_BD(nro_socio, codigo);
						coleccion_reservas.remove(codigo);
						coleccion_alquileres.add(alquiler);
						respuesta = "Alquiler Realizado";
					} catch (SQLException e) {
						respuesta = "Se produjo un error SQL";
						e.printStackTrace();
					} catch (Exception e) {
						respuesta = "Se produjo un error en la BD";
						e.printStackTrace();
					}

					
				}
				else {
					System.out.println("La pelicula esta reservada por el socio: " + nro_socio_que_reservo);
					respuesta = "La pelicula esta reservada por el socio: " + nro_socio_que_reservo;
				}
			}
			else {

					AlquilarPeliculas alquiler = new AlquilarPeliculas(socio,pelicula);
					
					try {
						manager_persistencia.alta_Alquiler_BD(nro_socio, codigo);
						coleccion_alquileres.add(alquiler);
						respuesta = "Alquiler Realizado";
						
					} catch (SQLException e) {
						respuesta = "Se produjo un error, verifique que la pelicula no este alquilada";
						
					} catch (Exception e) {
						respuesta = "Se produjo un error en la BD";
						
					}
					
					
					
				}
			
			}
		return respuesta;
		
	}
	
	

	public void probarConexion(Manager_persistencia manager_persistencia) {
		manager_persistencia.probarConexion();
	}
	public Hashtable getColeccionSocios() {
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.getColeccionSocios();
		
	}
	public Vector getColeccionAlquileres(){
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.getColeccionAlquileres();
	}
	public Hashtable getColeccionReservas() {
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.getColeccionReservas();
	}
	public Vector devolver_Vector_Socios() {
		System.out.println("\nListado de Socios (ordenados por nombre) \n");
		//Hashtable no admite ser ordenado, para que las peliculas salgan ordenadas por Titulo
		//creo un vector que contenga las peliculas, lo ordeno y lo imprimo
		Vector coleccion_socios_vector = new Vector();
		Hashtable coleccion_socios = manager_socio.getColeccionSocios();
		for (Enumeration e = coleccion_socios.elements(); e.hasMoreElements();){
			Socio element = (Socio) e.nextElement();
			coleccion_socios_vector.add(element);
		}
		Collections.sort(coleccion_socios_vector);
		return coleccion_socios_vector;
	}
	public Vector devolver_Vector_Reservas() {
		Vector coleccion_reservas_vector = new Vector();
		Hashtable coleccion_reservas = manager_socio.getColeccionReservas();
		for (Enumeration e = coleccion_reservas.elements(); e.hasMoreElements();){
			DatatypeReservas element = (DatatypeReservas) e.nextElement();
			coleccion_reservas_vector.add(element);
		}
//		comparable no implementado
//		Collections.sort(coleccion_reservas_vector);
		return coleccion_reservas_vector;
	}
}
