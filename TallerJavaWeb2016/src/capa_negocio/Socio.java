package capa_negocio;

public class Socio implements Comparable<Socio> {
	private String nombre;
	private String direccion;
	private int numeroDeSocio;
	
	public Socio (int numSoc, String nom, String dir){
		this.nombre = nom;
		this.direccion = dir;
		this.numeroDeSocio = numSoc;
	}
	public Socio() {
	
	}
	
	
	
	public Socio (String nom, String dir){
		this.nombre = nom;
		this.direccion = dir;
	}
	
	public String getNombre() {

		return nombre;
	}
	public String getDireccion() {
		
		return direccion;
	}
	public int getNroSocio() {
		return numeroDeSocio;
	}
	
	public void setNroSocio(int nro_socio){
		this.numeroDeSocio=nro_socio;
	}
	
	public int compareTo(Socio compareSocio){
		
		String compareNombre = compareSocio.getNombre();
		
		if (this.nombre.compareTo(compareNombre)>0) return 1;
		else {
			if (this.nombre.compareTo(compareNombre)<0) return -1;
			else return 0;
		}

	}
	public void imprimir() {
		// TODO Auto-generated method stub
		
	}
	
	
}
