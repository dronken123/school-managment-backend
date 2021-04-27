package com.school.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@NotEmpty(message = "no puede estar vacío.")
	@Size(min = 8, max = 8, message = " tiene que tener 8 caracteres.")
	private String dni;
	
	
	private String sexo;
	private String correo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Aula aula;

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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
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

	private static final long serialVersionUID = 1L;
}
