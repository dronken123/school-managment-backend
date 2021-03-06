package com.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.school.dao.NotaDao;
import com.school.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.school.service.MatriculaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaService matriculaService;

	@Autowired
	private NotaDao notaDao;
	
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
		response.put("mensaje", "La matr??cula se ha creado con ??xito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/niveles")
	public ResponseEntity<List<Nivel>> getNiveles(){
		return new ResponseEntity<>(matriculaService.getNiveles(), HttpStatus.OK);
	}

	@GetMapping("/turnos")
	public ResponseEntity<List<Turno>> getTurnos(){
		return new ResponseEntity<>(matriculaService.getTurnos(), HttpStatus.OK);
	}

	@GetMapping("/dias")
	public ResponseEntity<List<DiaSemana>> getDias(){
		return new ResponseEntity<>(matriculaService.getDias(), HttpStatus.OK);
	}

	@GetMapping("/notas")
	public ResponseEntity<List<Nota>> getNotas(@RequestParam("idCurso") String idCurso, @RequestParam("idAula") String idAula){
		return new ResponseEntity<>(notaDao.notasPorAulaYCurso(Long.parseLong(idCurso), Long.parseLong(idAula)), HttpStatus.OK);
	}

	@PutMapping("/notas")
	public ResponseEntity<?> UpdateNota(@RequestBody List<Nota> notas){
		List<Nota> notasActualizar = notas;
		Map<String, Object> response = new HashMap<>();


		try {
			notasActualizar = (List<Nota>) notaDao.saveAll(notas);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al ACTUALIZAR las notas en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Notas actualizadas con ??xito!");
		response.put("notas", notasActualizar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
