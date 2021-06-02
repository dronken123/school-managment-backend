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

import com.school.model.Clase;
import com.school.service.ClaseService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clases")
public class ClaseController {

	@Autowired
	private ClaseService claseService;
	
	@GetMapping
	public ResponseEntity<List<Clase>> getAllClases(){
		return new ResponseEntity<List<Clase>>(claseService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClase(@PathVariable Long id){
		
		Optional<Clase> clase = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clase = claseService.getClaseById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar la clase en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(clase.isEmpty()) {
			response.put("mensaje", "La clase con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Clase>(clase.get() ,HttpStatus.OK);
	}
	
	
	@PostMapping("/crear")
	public ResponseEntity<?> saveClase(@Valid @RequestBody Clase clase, BindingResult result){
		
		Clase claseNueva = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		try {
			claseNueva = claseService.save(clase);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar la clase en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("clase", claseNueva);
		response.put("mensaje", "La clase ha sido creada con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<?> updateClase(@Valid @RequestBody Clase clase, BindingResult result, @PathVariable Long id){
		
		Clase claseActual = claseService.getClaseById(id).orElse(null);
		Clase claseActualizada = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(er -> "El campo '" + er.getField() + "' " + er.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		
		if(claseActual == null) {
			response.put("mensaje", "Error: No se pudo editar la clase con el ID: ".concat(id.toString().concat(" no existe en la base de datos.")));
		}
		
		try {
			claseActual.setNombre(clase.getNombre());
			claseActual.setAula(clase.getAula());
			
			claseActualizada = claseService.update(claseActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar la clase en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("clase", claseActualizada);
		response.put("mensaje", "La clase ha sido actualizada con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClase(@PathVariable Long id){
		
		Clase clase = claseService.getClaseById(id).orElse(null);
		Map<String, Object> response = new HashMap<>();
		
		if(clase == null) {
			response.put("mensaje", "Error: No se pudo eliminar la clase con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			claseService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la clase en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La clase ha sido eliminada con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
