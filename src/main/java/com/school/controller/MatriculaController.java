package com.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.school.model.Apoderado;
import com.school.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.Matricula;
import com.school.service.MatriculaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaService matriculaService;
	
	@PostMapping("/crear")
	public ResponseEntity<?> saveMatricula(@Valid @RequestBody Matricula matricula, BindingResult results){
		
		Matricula matriculaNueva = null;
		Map<String, Object> response = new HashMap<>();
		
		if(results.hasErrors()) {
			List<String> errors = results.getFieldErrors()
					.stream()
					.map(er -> "El campo '" + er.getField() +"' " + er.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			Estudiante estudiante = matricula.getEstudiante();
			String[] nombres = estudiante.getNombres().split(" ");
			estudiante.setCorreo(nombres[0] + "." + estudiante.getApellidoPaterno());
			estudiante.setCorreo(estudiante.getCorreo().concat("@elamericano.edu.pe").toLowerCase());
			Apoderado apoderado = estudiante.getApoderado();
			estudiante.setApoderado(apoderado);
			matricula.setEstudiante(estudiante);
			matriculaNueva = matriculaService.save(matricula);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el aula en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		
		response.put("matricula", matriculaNueva);
		response.put("mensaje", "La matrícula se ha creado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
