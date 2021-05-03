package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.model.Clase;

public interface ClaseService {

	public Clase save(Clase clase);
	
	public Optional<Clase> getClaseById(Long id);

	public List<Clase> findAll();
	
	public boolean delete(Long id);
	
}
