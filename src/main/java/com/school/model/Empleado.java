package com.school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 40, message = "tiene que ser entre 2 y 15 caracteres.")
    private String nombres;

    @Column(name = "apellido_paterno")
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 15 caracteres.")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 15 caracteres.")
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    @NotNull(message = "no puede estar vacío.")
    private String fechaNacimiento;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 8, max = 8, message = " tiene que tener 8 caracteres.")
    private String dni;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 9, max = 9, message = " tiene que tener 8 caracteres.")
    private String celular;

    @NotEmpty(message = "no puede estar vacío.")
    private String domicilio;

    @NotEmpty(message = "no puede estar vacío.")
    private String sexo;

    private String correo;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empleado")
//    @JsonIgnoreProperties({"empleado" ,"hibernateLazyInitializer", "handler"})
//    private List<Clase> listaClases = new ArrayList<>();

    public Empleado() {
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

//    public List<Clase> getListaClases() {
//        return listaClases;
//    }
//
//    public void setListaClases(List<Clase> listaClases) {
//        this.listaClases = listaClases;
//    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
