package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo")
public class Tipo {
	@Id
	private int cod_tipo_atencion;
	private String nom_tipo_atencion;
	private Double precio;
	
	@Override
	public String toString() {
		return "Tipo [cod_tipo_atencion=" + cod_tipo_atencion + ", nom_tipo_atencion=" + nom_tipo_atencion + ", precio="
				+ precio + "]";
	}

	public int getCod_tipo_atencion() {
		return cod_tipo_atencion;
	}

	public void setCod_tipo_atencion(int cod_tipo_atencion) {
		this.cod_tipo_atencion = cod_tipo_atencion;
	}

	public String getNom_tipo_atencion() {
		return nom_tipo_atencion;
	}

	public void setNom_tipo_atencion(String nom_tipo_atencion) {
		this.nom_tipo_atencion = nom_tipo_atencion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
	
	
}
