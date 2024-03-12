/*
* @file HabitacionController.java;
* @Autor (c)2024 JuanRuiz
* @Created 5 mar. 2024,04:37:06
*/
package org.grupouno.msvcnegocio.controllers;

import jakarta.validation.Valid;
import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.grupouno.msvcnegocio.dto.HabitacionDTO;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.service.HabitacionService;
import org.grupouno.msvcnegocio.util.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habiService;

    /** ModelMapper para mapeo de DTOs. */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtiene una lista de todas las habitaciones.
     *
     * @return ResponseEntity con la lista de habitaciones y un mensaje de éxito
     */
    @GetMapping
    public ResponseEntity<?> listarHabitaciones(){
        List<Habitacion> habitaciones = habiService.listarHabitaciones();
        List<HabitacionDTO> habitacionDTOs = habitaciones.stream().map(habitacion->modelMapper.map(habitacion, HabitacionDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<HabitacionDTO>> response = new ApiResponse<>(true, "Lista de habitaciones obtenida con éxito", habitacionDTOs);
        return ResponseEntity.ok(response);
    }


    /**
     * Obtiene una habitacion por su ID.
     *
     * @param ID de la habitacion a buscar
     * @return ResponseEntity con la habitación encontrada y un mensaje de éxito
     * @throws EntityNotFoundException
     */
    @GetMapping("/{idHabitacion}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idHabitacion) throws EntityNotFoundException {
        Habitacion habitaciones = habiService.buscarPorIdHabitacion(idHabitacion);
        HabitacionDTO habitacionDTO = modelMapper.map(habitaciones, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación obtenida con éxito", habitacionDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crea una nueva habitación.
     *
     * @param  DTO de la habitación a crear
     * @return ResponseEntity con la habitación creada y un mensaje de éxito
     * @throws IllegalOperationException
     */
    @PostMapping
    public ResponseEntity<?> crearHabitacion(@RequestBody HabitacionDTO habitacionDTO, BindingResult result) throws IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.crearHabitacion(habitacion);
        HabitacionDTO savedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación creada con éxito", savedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualizar habitacion.
     *
     * @param id de la habitacion
     * @param  Información actualizada de la habitacion
     * @return La habitación actualizada
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PutMapping("/{idHabitacion}")
    public ResponseEntity<?> actualizarHabitacion(@Valid @RequestBody HabitacionDTO habitacionDTO, BindingResult result, @PathVariable Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Habitacion habitacion = modelMapper.map(habitacionDTO, Habitacion.class);
        habiService.actualizarHabitacion(idHabitacion, habitacion);
        HabitacionDTO updatedHabitacionDTO = modelMapper.map(habitacion, HabitacionDTO.class);
        ApiResponse<HabitacionDTO> response = new ApiResponse<>(true, "Habitación actualizada con con éxito",updatedHabitacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
