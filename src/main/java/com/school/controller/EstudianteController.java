package com.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.model.Estudiante;
import com.school.service.EstudianteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

	@Autowired
	private EstudianteService estudianteService;
	
	@GetMapping
	public ResponseEntity<List<Estudiante>> getAllEstudiantes(){
		return new ResponseEntity<>(estudianteService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> saveEstudiante(@Valid @RequestBody Estudiante estudiante, BindingResult results){
		Estudiante estudianteNuevo = null;
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
			estudianteNuevo = estudianteService.save(estudiante);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el estudiante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estudiante ha sido creado con éxito!");
		response.put("estudiante", estudianteNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEstudiante(@PathVariable Long id){
		
		Optional<Estudiante> estudiante = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			estudiante = estudianteService.getEstudianteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar el estudiante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(estudiante.isEmpty()) {
			response.put("mensaje", "El estudiante con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Estudiante>(estudiante.get(), HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEstudiante(@Valid @RequestBody Estudiante estudiante ,BindingResult results , @PathVariable Long id){
		Estudiante estudianteActual = estudianteService.getEstudianteById(id).get();
		Estudiante estudianteActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(results.hasErrors()) {
			List<String> errors = results.getFieldErrors()
					.stream()
					.map(er -> "El campo '" + er.getField() +"' " + er.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(estudianteActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el estudiante con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			estudianteActual.setNombres(estudiante.getNombres());
			estudianteActual.setApellidoPaterno(estudiante.getApellidoPaterno());
			estudianteActual.setApellidoMaterno(estudiante.getApellidoMaterno());
			estudianteActual.setDni(estudiante.getDni());
			estudianteActual.setFechaNacimiento(estudiante.getFechaNacimiento());
			estudianteActual.setCorreo(estudiante.getCorreo());
			estudianteActual.setSexo(estudiante.getSexo());
			
			estudianteActualizado = estudianteService.save(estudianteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estudiante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estudiante ha sido actualizado con éxito!");
		response.put("estudiante", estudianteActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEstudiante(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		Estudiante estudiante = estudianteService.getEstudianteById(id).orElse(null);
		
		if(estudiante == null) {
			response.put("mensaje", "Error: No se pudo eliminar, el estudiante con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			estudianteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el estudiante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estudiante ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}