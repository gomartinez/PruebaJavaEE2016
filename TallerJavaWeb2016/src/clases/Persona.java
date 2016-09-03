package clases;

import java.util.Date;


public class Persona {
	
	private String nombre;
	private Date fechaNac;
	private Pais pais;
	private EstadoCivil est_civil;
	private boolean trabaja;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public EstadoCivil getEst_civil() {
		return est_civil;
	}
	public void setEst_civil(EstadoCivil est_civil) {
		this.est_civil = est_civil;
	}
	public boolean isTrabaja() {
		return trabaja;
	}
	public void setTrabaja(boolean trabaja) {
		this.trabaja = trabaja;
	}

}
