package com.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Matricula;

public interface MatriculaDao extends JpaRepository<Matricula, Long>{

}
