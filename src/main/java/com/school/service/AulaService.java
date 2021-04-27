package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.model.Aula;

public interface AulaService {

	public Aula save(Aula aula);
	
	public Optional<Aula> getAulaById(Long id);

	public List<Aula> findAll();
	
	public boolean delete(Long id);
}
