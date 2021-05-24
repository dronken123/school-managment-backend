package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.model.Matricula;

public interface MatriculaService {
	
	public Matricula save(Matricula matricula);
	
	public Optional<Matricula> getMatriculaById(Long id);

	public List<Matricula> findAll();
	
	public boolean delete(Long id);
}
