package capa_negocio;

public class DatatypeAlquileres {
	private int socio;
	private String nombre;
	private String codigo;
	private String titulo;
	public DatatypeAlquileres (int socio, String nombre, String codigo, String titulo){
		this.socio = socio;
		this.nombre = nombre;
		this.codigo = codigo;
		this.titulo = titulo;
	}
	
	public int getSocio(){
		return socio;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	public String getTitulo(){
		return titulo;
	}
}
