package capa_negocio;




import java.util.Hashtable;
import java.util.Vector;


public class Pelicula implements Comparable<Pelicula> {
	private String codigo;
	private String titulo;
	private Vector actores;
	private String genero;
	public Pelicula (){};
	
	
	public Pelicula (String tit, Vector act, String gen){
		this.titulo=tit;
		this.actores=act;
		this.genero=gen;
	}
	public Pelicula(String codigo, String titulo, String actores, String genero) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.genero = genero;
		Vector <String> vector_actores = new Vector();
		String[] array_actores;	
		array_actores = actores.split(",");
	    for(int i = 0; i < array_actores.length; ++i) {
	        vector_actores.add(array_actores[i].trim());
	    }
	    this.actores = vector_actores;
		
	}


	public void setCodigoPelicula(String cod){
		this.codigo=cod;
	}
	public String getCodigo(){
		return codigo;
	}
	public String getTitulo(){
		return titulo;	
	}
	public void setTitulo(String tit){
		this.titulo=tit;
	}
	public String getGenero(){
		return this.genero;	
	}
	
	public void setGenero(String gen){
		this.genero=gen;
	}
	
	public void setActores(Vector act){
		this.actores=act;
	}
	
	public Vector getActores(){
		return this.actores;	
	}
	
	public String getCodigo(Pelicula pel){
		return this.codigo;	
	}
	
	public void setEstado(String estado){
	}
	
	public String getEstado(){
		return "";
	}
	
	public String toString(){
		return (titulo + " (" + codigo + ")");
	}
	
	public void imprimir(){
		String titulo;
		String genero;
		String codigo;

		
		Vector vector_actores = new Vector();

		titulo = this.getTitulo();
		vector_actores = this.getActores();
		genero = this.getGenero();
		codigo = this.getCodigo();

		System.out.print("Codigo: " + codigo);
		System.out.print("\t" + "Titulo: " + titulo);
		if (titulo.length()<20){
			System.out.print("\t");
		}
		if (titulo.length()<14){
			System.out.print("\t");
		}
		if (titulo.length()<6){
			System.out.print("\t");
		}
		System.out.print("Actores: ");
		
		String actores = "";
		for (int j=0;j<vector_actores.size();j++){
			String actor=(String) vector_actores.get(j);
			System.out.print(actor);
			actores = actores + actor;
			if (j<vector_actores.size()-1){
				System.out.print(", ");
				actores = actores + ", ";
			}
		}
		
		if (actores.length()<54){
			System.out.print("\t");
		}
		if (actores.length()<46){
			System.out.print("\t");
		}
		if (actores.length()<38){
			System.out.print("\t");
		}
		if (actores.length()<30){
			System.out.print("\t");
		}
		if (actores.length()<22){
			System.out.print("\t");
		}
		if (actores.length()<14){
			System.out.print("\t");
		}
		if (actores.length()<6){
			System.out.print("\t");
		}


		
		System.out.print("\t"+ "Genero: " + genero);
		System.out.println("");	

	}


	public int compareTo(Pelicula comparePelicula){
		
		String compareTitulo = comparePelicula.getTitulo();
		
		if (this.titulo.compareTo(compareTitulo)>0) return 1;
		else {
			if (this.titulo.compareTo(compareTitulo)<0) return -1;
			else return 0;
		}

	}	

	
}
