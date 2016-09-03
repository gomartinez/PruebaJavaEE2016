package capa_negocio;

import java.util.Hashtable;

import capa_persistencia.Manager_persistencia;

public class Manager_BaseDatos {
	//Singleton Manager_BaseDatos
	private static Manager_BaseDatos manager_baseDatos;
	private Manager_BaseDatos(){
		}
	public static Manager_BaseDatos getManagerBaseDatos(){
		 if (manager_baseDatos==null) {
		 	 manager_baseDatos=new Manager_BaseDatos();
		 }
		 return manager_baseDatos;
	}
	
	public String crearBaseDatos() {
		Manager_persistencia manager_persistencia = Manager_persistencia.getManagerPersistencia();
		return manager_persistencia.crearBaseDatos();
	}

}
