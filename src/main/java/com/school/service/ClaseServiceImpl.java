package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.dao.MaterialDao;
import com.school.dao.NotaDao;
import com.school.model.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.AulaDao;
import com.school.dao.ClaseDao;
import com.school.model.Clase;

@Service
public class ClaseServiceImpl implements ClaseService{

	@Autowired
	private ClaseDao claseDao;
	
	@Autowired
	private AulaDao aulaDao;

	@Autowired
	private NotaDao notaDao;

	@Override
	@Transactional
	public Clase save(Clase clase) {
		// TODO Auto-generated method stub
		clase.setAula(aulaDao.findById(clase.getAula().getId()).get());
		clase.getFrecuencias().stream().forEach(f -> f.setClase(clase));
		return claseDao.save(clase);
	}
	

	@Override
	@Transactional
	public Clase update(Clase clase) {
		// TODO Auto-generated method stub
		return claseDao.save(clase);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Clase> getClaseById(Long id) {
		// TODO Auto-generated method stub
		return claseDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Clase> findAll() {
		// TODO Auto-generated method stub
		return (List<Clase>) claseDao.findAll();
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return getClaseById(id).map(c -> {
			claseDao.deleteById(id);
			return true;
		}).orElse(false);
	}

	@Override
	public Nota saveNota(Nota nota) {
		return notaDao.save(nota);
	}

}
