package com.school.controller;

import com.school.model.Empleado;
import com.school.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoService empleadoService;


    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados(){
        return new ResponseEntity<>(empleadoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> saveEmpleado(@Valid @RequestBody Empleado empleado, BindingResult results){
        Empleado empleadoNuevo = null;
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
            empleadoNuevo = empleadoService.save(empleado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el empleado en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El empleado ha sido creado con éxito!");
        response.put("empleado", empleadoNuevo);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable Long id){

        Optional<Empleado> empleado = null;
        Map<String, Object> response = new HashMap<>();

        try {
            empleado = empleadoService.getEmpleadoById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el empleado en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(empleado.isEmpty()) {
            response.put("mensaje", "El Empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Empleado>(empleado.get(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmpleado(@Valid @RequestBody Empleado empleado ,BindingResult results , @PathVariable Long id){
        Empleado empleadoActual = empleadoService.getEmpleadoById(id).get();
        Empleado empleadoActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if(results.hasErrors()) {
            List<String> errors = results.getFieldErrors()
                    .stream()
                    .map(er -> "El campo '" + er.getField() +"' " + er.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(empleadoActual == null) {
            response.put("mensaje", "Error: No se pudo editar, el empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            empleadoActual.setNombres(empleado.getNombres());
            empleadoActual.setApellidoPaterno(empleado.getApellidoPaterno());
            empleadoActual.setApellidoMaterno(empleado.getApellidoMaterno());
            empleadoActual.setDni(empleado.getDni());
            empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
            empleadoActual.setCorreo(empleado.getCorreo());
            empleadoActual.setSexo(empleado.getSexo());
            empleadoActual.setDomicilio(empleado.getDomicilio());

            empleadoActualizado = empleadoService.save(empleadoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el Empleado en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El empleado ha sido actualizado con éxito!");
        response.put("empleado", empleadoActualizado);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        Empleado empleado = empleadoService.getEmpleadoById(id).orElse(null);

        if(empleado == null) {
            response.put("mensaje", "Error: No se pudo eliminar, el empleado con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            empleadoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el empleado en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El empleado ha sido eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
}