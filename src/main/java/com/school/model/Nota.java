package com.school.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notas")
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nota_bim1;
    private String nota_bim2;
    private String nota_bim3;
    private String nota_bim4;
    private String promedio_final;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNota_bim1() {
        return nota_bim1;
    }

    public void setNota_bim1(String nota_bim1) {
        this.nota_bim1 = nota_bim1;
    }

    public String getNota_bim2() {
        return nota_bim2;
    }

    public void setNota_bim2(String nota_bim2) {
        this.nota_bim2 = nota_bim2;
    }

    public String getNota_bim3() {
        return nota_bim3;
    }

    public void setNota_bim3(String nota_bim3) {
        this.nota_bim3 = nota_bim3;
    }

    public String getNota_bim4() {
        return nota_bim4;
    }

    public void setNota_bim4(String nota_bim4) {
        this.nota_bim4 = nota_bim4;
    }

    public String getPromedio_final() {
        return promedio_final;
    }

    public void setPromedio_final(String promedio_final) {
        this.promedio_final = promedio_final;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
