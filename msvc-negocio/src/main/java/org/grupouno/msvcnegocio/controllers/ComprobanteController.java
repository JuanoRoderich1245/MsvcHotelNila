/*
 * @file ComprobanteController.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 5 mar. 2024,03:32:34
 */
package org.grupouno.msvcnegocio.controllers;

import jakarta.validation.Valid;
import org.grupouno.msvcnegocio.dominio.Comprobante;
import org.grupouno.msvcnegocio.dto.ComprobanteDTO;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.service.ComprobanteService;
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
@RequestMapping("/api/comprobantes")
public class ComprobanteController {


    /** The comprobante service. */
    @Autowired
    private ComprobanteService comprobanteService;

    /** The model mapper. */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Listar comprobantes.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<?> listarComprobantes(){
        List<Comprobante> comprobantes = comprobanteService.listarComprobantes();
        List<ComprobanteDTO> comprobanteDTOs = comprobantes.stream().map(comprobante->modelMapper.map(comprobante, ComprobanteDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<ComprobanteDTO>> response = new ApiResponse<>(true, "Lista de comprobantes obtenida con éxito", comprobanteDTOs);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar por ID.
     *
     * @param idComprobante the id comprobante
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     */
    @GetMapping("/{idComprobante}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idComprobante) throws EntityNotFoundException {
        Comprobante comprobantes = comprobanteService.buscarPorIdComprobante(idComprobante);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobantes, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Comprobante obtenido con éxito", comprobanteDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crear comprobante.
     *
     * @param comprobanteDTO the comprobante DTO
     * @param result the result
     * @return the response entity
     * @throws IllegalOperationException the illegal operation exception
     */
    @PostMapping
    public ResponseEntity<?> crearComprobante(@Valid @RequestBody ComprobanteDTO comprobanteDTO, BindingResult result ) throws IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Comprobante comprobante = modelMapper.map(comprobanteDTO, Comprobante.class);
        comprobanteService.crearComprobante(comprobante);
        ComprobanteDTO savedComprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Comprobante creada con éxito", savedComprobanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualizar comprobante.
     *
     * @param comprobanteDTO the comprobante DTO
     * @param result the result
     * @param idComprobante the id comprobante
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @PutMapping("/{idComprobante}")
    public ResponseEntity<?> actualizarComprobante(@Valid @RequestBody ComprobanteDTO comprobanteDTO,BindingResult result, @PathVariable Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Comprobante comprobante = modelMapper.map(comprobanteDTO, Comprobante.class);
        comprobanteService.actualizarComprobante(idComprobante,comprobante);
        ComprobanteDTO updatedComprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Cliente actualizado con éxito",updatedComprobanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Eliminar comprobante.
     *
     * @param idComprobante the id comprobante
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @DeleteMapping("/{idComprobante}")
    public ResponseEntity<?> eliminarComprobante(@PathVariable Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
        comprobanteService.eliminarComprobante(idComprobante);
        ApiResponse<String> response = new ApiResponse<>(true, "Comprobante eliminada con éxito", null);
        return ResponseEntity.ok(response);
    }

    /**
     * Asignar reserva.
     *
     * @param idComprobante the id comprobante
     * @param idReserva the id reserva
     * @return the response entity
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @PutMapping(value = "/asignarReserva/{idComprobante}/{idReserva}")
    public ResponseEntity<?> asignarReserva (@PathVariable Long idComprobante, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        Comprobante comprobante = comprobanteService.asignarReserva(idComprobante, idReserva);
        ComprobanteDTO comprobanteDTO = modelMapper.map(comprobante, ComprobanteDTO.class);
        ApiResponse<ComprobanteDTO> response = new ApiResponse<>(true, "Reserva asignada con éxito", comprobanteDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Validar.
     *
     * @param result the result
     * @return the response entity
     */
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}