package com.school.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "estudiantes")
public class Estudiante implements Serializable {

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

	@NotNull(message = "no puede estar vacío.")
	@Column(name = "fecha_nacimiento")
	private String fechaNacimiento;

	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 8, max = 8, message = " tiene que tener 8 caracteres.")
	private String dni;

	@NotEmpty(message = "no puede estar vacío")
	private String domicilio;

	@NotEmpty(message = "no puede estar vacío")
	private String celular;

	@NotEmpty(message = "no puede estar vacío")
	private String sexo;

	private String correo;

	@ManyToOne
	@JoinColumn(nullable = true)
	@JsonIgnoreProperties({ "listaEstudiantes", "hibernateLazyInitializer", "handler" })
	private Aula aulaEstudiante;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "apoderado_id", nullable = false)
	@NotNull(message = "no puede estar vacío")
	private Apoderado apoderado;

	@OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
	private List<Matricula> listaMatriculas = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "grado_id", nullable = false)
	private Grado grado;

	public Estudiante() {
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Aula getAulaEstudiante() {
		return aulaEstudiante;
	}

	public void setAulaEstudiante(Aula aula) {
		this.aulaEstudiante = aula;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public List<Matricula> getListaMatriculas() {
		return listaMatriculas;
	}

	public void setListaMatriculas(List<Matricula> listaMatriculas) {
		this.listaMatriculas = listaMatriculas;
	}

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	private static final long serialVersionUID = 1L;
}
