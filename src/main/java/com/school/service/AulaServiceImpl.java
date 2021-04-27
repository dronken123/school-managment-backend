package com.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.AulaDao;
import com.school.model.Aula;

@Service
public class AulaServiceImpl implements AulaService{

	@Autowired
	private AulaDao aulaDao;
	
	@Override
	@Transactional
	public Aula save(Aula aula) {
		return aulaDao.save(aula);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aula> getAulaById(Long id) {
		return aulaDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aula> findAll() {
		return (List<Aula>) aulaDao.findAll();
	}

	@Override
	@Transactional
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return getAulaById(id).map(a ->  {
			aulaDao.deleteById(id);
			return true;
		}).orElse(false);
	}

}
