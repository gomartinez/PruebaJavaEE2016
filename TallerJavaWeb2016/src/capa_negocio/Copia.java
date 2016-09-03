package capa_negocio;
import java.util.Vector;


public class Copia extends Pelicula{
	String estado;
	public Copia (){
	}
	public Copia(String codigo, String titulo, String actores, String genero, String estado) {
		super (codigo, titulo, actores, genero);
		this.estado=estado;
	}
	public String getEstado(){
		return this.estado;
	}
	public void setEstado(String estado){
		this.estado=estado;
	}
	
	
	
	public void imprimir(){
		String titulo;
		String genero;
		String estado;
		String codigo;
		Pelicula elem;
		Copia elemCopia;
		
		Vector vector_actores = new Vector();

		estado ="";
		titulo = this.getTitulo();
		vector_actores = this.getActores();
		genero = this.getGenero();
		codigo = this.getCodigo();
		estado = this.getEstado();
		
		System.out.print("Codigo: " + codigo);
		System.out.print("\t" + "Titulo: " + titulo);
		
		if (titulo.length()<24){
			System.out.print("\t");
		}
		if (titulo.length()<16){
			System.out.print("\t");
		}
		if (titulo.length()<8){
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
		if (actores.length()<16){
			System.out.print("\t");
		}
		if (actores.length()<6){
			System.out.print("\t");
		}
		
		System.out.print("\t"+ "Genero: " + genero);
		
		System.out.print("   Estado: " + estado);
		System.out.println("");	

	}
	

}
