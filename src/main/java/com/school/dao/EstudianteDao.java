package com.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Estudiante;

public interface EstudianteDao extends JpaRepository<Estudiante, Long>{

}
