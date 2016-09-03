package capa_negocio;

public class DatatypeAltaSocio {
	private int socio;
	private String mensaje;
	public DatatypeAltaSocio (int socio, String mensaje){
		this.socio = socio;
		this.mensaje = mensaje;
	}
	
	public DatatypeAltaSocio() {
		
	}

	public int getSocio(){
		return socio;
	}
	
	public String getMensaje(){
		return mensaje;
	}
	
	public void setSocio(int socio){
		this.socio=socio;
	}
	
	public void setMensaje(String mensaje){
		this.mensaje=mensaje;
	}
}
