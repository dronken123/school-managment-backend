package com.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "aulas")
public class Aula implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
	private String nombre;

	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 1, max = 10, message = "tiene que ser entre 2 y 10 caracteres.")
	private String seccion;

	@NotEmpty(message = "no puede estar vacío.")
	private String nivel;

	@NotEmpty(message = "no puede estar vacío.")
	private String turno;

	@NotNull(message = "no puede estar vacío")
	private Integer capacidad;

	@NotNull(message = "no puede estar vacío")
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "aulas", "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "grado_id")
	private Grado gradoAula;

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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Grado getGradoAula() {
		return gradoAula;
	}

	public void setGradoAula(Grado gradoAula) {
		this.gradoAula = gradoAula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
