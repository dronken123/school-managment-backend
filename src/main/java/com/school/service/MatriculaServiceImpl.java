package com.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.MatriculaDao;
import com.school.model.Apoderado;
import com.school.model.Estudiante;
import com.school.model.Matricula;

@Service
public class MatriculaServiceImpl implements MatriculaService{

	@Autowired
	private MatriculaDao matriculaDao;

	@Override
	@Transactional(readOnly = false)
	public Matricula save(Matricula matricula) {
		
		Estudiante estudiante = matricula.getEstudiante();
		String[] nombres = estudiante.getNombres().split(" ");
		estudiante.setCorreo(nombres[0] + "." + estudiante.getApellidoPaterno());
		estudiante.setCorreo(estudiante.getCorreo().concat("@elamericano.edu.pe").toLowerCase());
		
		Apoderado apoderado = estudiante.getApoderado();
		estudiante.setApoderado(apoderado);
		matricula.setEstudiante(estudiante);
		
		return matriculaDao.save(matricula);
	}

	@Override
	@Transactional
	public Optional<Matricula> getMatriculaById(Long id) {
		// TODO Auto-generated method stub
		return matriculaDao.findById(id);
	}

	@Override
	@Transactional
	public List<Matricula> findAll() {
		// TODO Auto-generated method stub
		return matriculaDao.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return getMatriculaById(id).map(m -> {
			matriculaDao.deleteById(id);
			return true;
		}).orElse(false);
	}
	


}
