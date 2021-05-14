package com.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "grado", "hibernateLazyInitializer", "handler" })
	@JoinColumn(name = "grado_id")
	private Grado grado;

	@OneToMany(mappedBy = "aulaEstudiante", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "aulaEstudiante", "hibernateLazyInitializer", "handler" })
	private List<Estudiante> listaEstudiantes;

	@OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "aula", "hibernateLazyInitializer", "handler" })
	private List<Clase> clasesAula = new ArrayList<>();

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

	public List<Clase> getClasesAula() {
		return clasesAula;
	}

	public void setClasesAula(List<Clase> clases) {
		this.clasesAula = clases;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
