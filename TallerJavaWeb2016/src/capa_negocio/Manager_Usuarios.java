package capa_negocio;


import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Arrays;
import java.util.Hashtable;


import capa_persistencia.Manager_persistencia;

public class Manager_Usuarios {
	
	//Singleton Manager_Pelicula
	private static Manager_Usuarios manager_usuarios;
	private Manager_Usuarios(){
		}
	public static Manager_Usuarios getManagerUsuarios(){
		 if (manager_usuarios==null) {
			 manager_usuarios=new Manager_Usuarios();
		 }
		 return manager_usuarios;
	}
	

	public boolean ExisteUsuario(String nombre, String contrasena) {
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.ExisteUsuario(nombre, contrasena);
	}

	

	
	
}
