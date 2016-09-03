package capa_negocio;

public class DatatypeReservas {
	private int socio;
	private String codigo;
	private String nombre;
	private String titulo;
	public DatatypeReservas (int socio, String nombre, String codigo, String titulo){
		this.socio = socio;
		this.codigo = codigo;
		this.titulo = titulo;
		this.nombre = nombre;
	}
	public int getSocio(){
		return socio;
	}
	
	public String getCodigo(){
		return codigo;
	}
	
	
	public String getTitulo(){
		return titulo;
	}
	
	public String getNombre(){
		return nombre;
	}
}
