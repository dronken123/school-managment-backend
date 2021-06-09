package com.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.EstudianteDao;
import com.school.model.Estudiante;

@Service
public class EstudianteServiceImpl implements EstudianteService{

	@Autowired
	private EstudianteDao estudianteDao;
	
	@Override
	@Transactional
	public Estudiante save(Estudiante estudiante) {
		return estudianteDao.save(estudiante);
	}

	@Override
	public Estudiante findByDni(String dni) {
		return estudianteDao.findByDni(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Estudiante> getEstudianteById(Long id) {
		return estudianteDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estudiante> findAll() {
		return estudianteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Estudiante> findAll(Pageable pageable) {
		return estudianteDao.findAll(pageable);
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		return getEstudianteById(id).map(estudiante -> {
			estudianteDao.deleteById(id);
			return true;
		}).orElse(false);
	}

	
}
