package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="atencion")

public class Atencion {

	@Id
	private String num_atencion;
	private String fecha;
	private String nom_paciente;
	private int cod_tipo_atencion;
	
	@ManyToOne
	@JoinColumn(name="cod_tipo_atencion", insertable = false, updatable = false)
	private Tipo objTipo;

	public Atencion() {
	}

	
		
	
	public Atencion(String num_atencion, String fecha, String nom_paciente, int cod_tipo_atencion, Tipo objTipo) {
		this.num_atencion = num_atencion;
		this.fecha = fecha;
		this.nom_paciente = nom_paciente;
		this.cod_tipo_atencion = cod_tipo_atencion;
		this.objTipo = objTipo;
	}




	@Override
	public String toString() {
		return "Atencion [num_atencion=" + num_atencion + ", fecha=" + fecha + ", nom_paciente=" + nom_paciente
				+ ", cod_tipo_atencion=" + cod_tipo_atencion + ", objTipo=" + objTipo + "]";
	}




	public String getNum_atencion() {
		return num_atencion;
	}




	public void setNum_atencion(String num_atencion) {
		this.num_atencion = num_atencion;
	}




	public String getFecha() {
		return fecha;
	}




	public void setFecha(String fecha) {
		this.fecha = fecha;
	}




	public String getNom_paciente() {
		return nom_paciente;
	}




	public void setNom_paciente(String nom_paciente) {
		this.nom_paciente = nom_paciente;
	}




	public int getCod_tipo_atencion() {
		return cod_tipo_atencion;
	}




	public void setCod_tipo_atencion(int cod_tipo_atencion) {
		this.cod_tipo_atencion = cod_tipo_atencion;
	}




	public Tipo getObjTipo() {
		return objTipo;
	}




	public void setObjTipo(Tipo objTipo) {
		this.objTipo = objTipo;
	}




	

	
	
}
