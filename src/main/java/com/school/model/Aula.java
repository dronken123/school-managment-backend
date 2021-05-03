package com.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "aulas")
public class Aula implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 10, message = "tiene que ser entre 2 y 10 caracteres.")
	private String seccion;
	
	@OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"aula", "hibernateLazyInitializer", "handler"})
	private List<Estudiante> listaEstudiantes;
	
	@OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"aula", "hibernateLazyInitializer", "handler"})
	private List<Clase> clases = new ArrayList<>();
	
	public Aula() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}
	
	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
