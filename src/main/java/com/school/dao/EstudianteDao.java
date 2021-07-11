package com.school.dao;

import com.school.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Estudiante;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteDao extends JpaRepository<Estudiante, Long>{

    @Query("FROM Estudiante e where e.usuario.username = ?1 and e.usuario.password = ?2 ")
    public Estudiante loginUsuario(String username, String password);

    public Estudiante findByDniAndDni(String username, String password);

    @Query(value = "Select * FROM notas n WHERE estudiante.id = ?1", nativeQuery = true)
    public List<Nota> findNotasEstudiante(Long id);
}
