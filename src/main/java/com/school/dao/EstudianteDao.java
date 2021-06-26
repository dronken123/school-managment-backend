package com.school.dao;

import com.school.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Estudiante;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteDao extends JpaRepository<Estudiante, Long>{

    public Estudiante findByDni(String dni);

    @Query(value = "Select * FROM notas n WHERE estudiante.id = ?1", nativeQuery = true)
    public List<Nota> findNotasEstudiante(Long id);
}
