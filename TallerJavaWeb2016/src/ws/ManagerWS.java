package ws;

import java.util.Hashtable;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import capa_negocio.DatatypeAltaSocio;
import capa_negocio.Manager_Socio;

@WebService
public class ManagerWS {

	@WebMethod
	public DatatypeAltaSocio alta_Socio(String nombre, String direccion) {
		Manager_Socio ms = Manager_Socio.getManagerSocio();
		return ms.alta_Socio(nombre, direccion);		 
	}

//	public Hashtable devolver_Vector_Socios() {
//		Manager_Socio ms = Manager_Socio.getManagerSocio();
//		Vector coleccion_socios_vector = ms.devolver_Vector_Socios();
//	}
//	public Vector getColeccionAlquileres(){
//		Manager_Socio ms = Manager_Socio.getManagerSocio();
//	}
//	public Hashtable getColeccionReservas(){
//		Manager_Socio ms = Manager_Socio.getManagerSocio();
//	}
}
