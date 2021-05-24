package com.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "apoderados")
public class Apoderado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 40, message = "tiene que ser entre 2 y 40 caracteres.")
	private String nombres;

	@Column(name = "apellido_paterno")
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 20, message = "tiene que ser entre 2 y 15 caracteres.")
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno")
	@NotEmpty(message = "no puede estar vacío")
	@Size(min = 2, max = 20, message = "tiene que ser entre 2 y 15 caracteres.")
	private String apellidoMaterno;
	
	@NotEmpty(message = "no puede estar vacío")
	private String dni;
	
	@NotEmpty(message = "no puede estar vacío")
	private String celular;

	@OneToMany(mappedBy = "apoderado", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "apoderado", "hibernateLazyInitializer", "handler" })
	private List<Estudiante> listaEstudianteM = new ArrayList<>();

	public Apoderado() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public List<Estudiante> getListaEstudianteM() {
		return listaEstudianteM;
	}

	public void setListaEstudianteM(List<Estudiante> listaEstudianteM) {
		this.listaEstudianteM = listaEstudianteM;
	}

	private static final long serialVersionUID = 1L;
}
