package com.school.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clases")
public class Clase implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "no puede estar vacío.")
	@JoinColumn(name = "aula_id")
	private Aula aula;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso", nullable = false)
	@JsonIgnoreProperties({"clases", "hibernateLazyInitializer", "handler"})
	private Curso curso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = " empleado_id", nullable = true)
	private Empleado empleado;
	
	public Clase() {
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

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
