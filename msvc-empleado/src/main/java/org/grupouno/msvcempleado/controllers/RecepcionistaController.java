/*
 * @file RecepcionistaController.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 5 mar. 2024,00:10:13
 */

package org.grupouno.msvcempleado.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.grupouno.msvcempleado.dominio.Recepcionista;
import org.grupouno.msvcempleado.dto.RecepcionistaDTO;
import org.grupouno.msvcempleado.exception.IllegalOperationException;
import org.grupouno.msvcempleado.service.RecepcionistaService;
import org.grupouno.msvcempleado.util.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
// TODO: Auto-generated Javadoc
/**
 * La clase RecepcionistaController proporciona endpoints para operaciones relacionadas con recepcionistas.
 */
@RestController
@RequestMapping("/api/recepcionistas")
public class RecepcionistaController {
    /** El servicio de recepcionista. */
    @Autowired
    private RecepcionistaService recepcionistaService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtiene una lista de todos los recepcionistas.
     *
     * @return ResponseEntity con la lista de recepcionistas y un mensaje de éxito
     */
    @GetMapping
    public ResponseEntity<?> listarRecepcionistas() {
        List<Recepcionista> recepcionistas = recepcionistaService.listarRecepcionistas();
        List<RecepcionistaDTO> recepcionistaDTOs = recepcionistas.stream().map(recepcionista -> modelMapper.map(recepcionista, RecepcionistaDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<RecepcionistaDTO>> response = new ApiResponse<>(true, "Lista de recepcionistas obtenida con éxito", recepcionistaDTOs);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene un recepcionista por su ID.
     *
     * @param idRecepcionista el ID del recepcionista a buscar
     * @return ResponseEntity con el recepcionista encontrado y un mensaje de éxito
     * @throws EntityNotFoundException si no se encuentra la entidad
     */
    @GetMapping("/{idRecepcionista}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idRecepcionista) throws EntityNotFoundException {
        Recepcionista recepcionista = recepcionistaService.buscarPorIdRecepcionista(idRecepcionista);
        RecepcionistaDTO recepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista obtenido con éxito", recepcionistaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crea un nuevo recepcionista.
     *
     * @param recepcionistaDTO el DTO del recepcionista a crear
     * @param result el resultado
     * @return ResponseEntity con el recepcionista creado y un mensaje de éxito
     * @throws IllegalOperationException si la operación es ilegal
     */
    @PostMapping
    public ResponseEntity<?> crearRecepcionista(@Valid @RequestBody RecepcionistaDTO recepcionistaDTO,  BindingResult result) throws IllegalOperationException {

        if(result.hasErrors()) {
            return validar(result);
        }

        Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.crearRecepcionista(recepcionista);
        RecepcionistaDTO savedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista creado con éxito", savedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualizar recepcionista.
     *
     * @param recepcionistaDTO el DTO del recepcionista
     * @param result el resultado
     * @param idRecepcionista el ID del recepcionista
     * @return ResponseEntity con el recepcionista actualizado y un mensaje de éxito
     * @throws IllegalOperationException si la operación es ilegal
     * @throws EntityNotFoundException si no se encuentra la entidad
     */
    @PutMapping("/{idRecepcionista}")
    public ResponseEntity<?> actualizarRecepcionista(@Valid @RequestBody RecepcionistaDTO recepcionistaDTO, BindingResult result, @PathVariable Long idRecepcionista) throws IllegalOperationException, EntityNotFoundException {

        if(result.hasErrors()) {
            return validar(result);
        }

        // Validar lógica de negocio
        if (recepcionistaDTO.getApePat() == null || recepcionistaDTO.getApePat().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'apePat' no puede estar vacío"));
        }

        if (recepcionistaDTO.getApeMat() == null || recepcionistaDTO.getApeMat().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'apeMat' no puede estar vacío"));
        }

        if (recepcionistaDTO.getNombre() == null || recepcionistaDTO.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'nombre' no puede estar vacío"));
        }

        if (recepcionistaDTO.getTelefono() == null || recepcionistaDTO.getTelefono().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'telefono' no puede estar vacío"));
        }

        if (!recepcionistaDTO.getTurno().equals("Mañana") && !recepcionistaDTO.getTurno().equals("Tarde")) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "El campo 'turno' debe ser 'Mañana' o 'Tarde'"));
        }

        Recepcionista recepcionista = modelMapper.map(recepcionistaDTO, Recepcionista.class);
        recepcionistaService.actualizarRecepcionista(idRecepcionista, recepcionista);
        RecepcionistaDTO updatedRecepcionistaDTO = modelMapper.map(recepcionista, RecepcionistaDTO.class);
        ApiResponse<RecepcionistaDTO> response = new ApiResponse<>(true, "Recepcionista actualizado con éxito", updatedRecepcionistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Elimina un recepcionista por su ID.
     *
     * @param idRecepcionista el ID del recepcionista a eliminar
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException si no se encuentra la entidad
     * @throws IllegalOperationException si la operación es ilegal
     */
    @DeleteMapping("/{idRecepcionista}")
    public ResponseEntity<?> eliminarRecepcionista(@PathVariable Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {
        recepcionistaService.eliminarRecepcionista(idRecepcionista);
        ApiResponse<String> response = new ApiResponse<>(true, "Recepcionista eliminado con éxito", null);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
