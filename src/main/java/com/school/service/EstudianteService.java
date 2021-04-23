package com.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.school.model.Estudiante;


public interface EstudianteService {

	public Estudiante save(Estudiante estudiante);
	
	public Optional<Estudiante> getEstudianteById(Long id);

	public List<Estudiante> findAll();
	
	public Page<Estudiante> findAll(Pageable pageable);
	
	public boolean delete(Long id);
}
