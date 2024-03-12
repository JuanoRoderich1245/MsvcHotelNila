/*
 * @file ReservaController.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,04:37:06
 */
package org.grupouno.msvcnegocio.controllers;

import jakarta.validation.Valid;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.service.ReservaService;
import org.grupouno.msvcnegocio.dto.ReservaDTO;
import org.grupouno.msvcnegocio.dominio.Reserva;
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
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService resService;

    /** ModelMapper para mapeo de DTOs. */
    @Autowired
    private ModelMapper modelMapper;

    /*
     * Obtiene una lista de todas las reservas.
     *
     * @return ResponseEntity con la lista de reservas y un mensaje de éxito
     */
    @GetMapping
    public ResponseEntity<?> listarReservas(){
        List<Reserva> reservas = resService.listarReservas();
        List<ReservaDTO> reservaDTOs = reservas.stream().map(reserva->modelMapper.map(reserva, ReservaDTO.class))
                .collect(Collectors.toList());
        ApiResponse<List<ReservaDTO>> response = new ApiResponse<>(true, "Lista de reservas obtenida con éxito", reservaDTOs);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtiene una reserva por su ID.
     *
     * @param ID de la reserva a buscar
     * @return ResponseEntity con la reserva encontrada y un mensaje de éxito
     * @throws EntityNotFoundException
     */
    @GetMapping("/{idReserva}")
    public ResponseEntity<?> listarPorID(@PathVariable Long idReserva) throws EntityNotFoundException {
        Reserva reservas = resService.buscarPorIdReserva(idReserva);
        ReservaDTO reservaDTO = modelMapper.map(reservas, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva obtenida con éxito", reservaDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Crea una nueva reserva.
     *
     * @param el DTO de la reserva a crear
     * @return ResponseEntity con la reserva creada y un mensaje de éxito
     * @throws IllegalOperationException
     */
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody ReservaDTO reservaDTO, BindingResult result) throws IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        resService.crearReserva(reserva);
        ReservaDTO savedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva creada con éxito", savedReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualizar reserva.
     *
     * @param id de la reserva
     * @param  Información actualizada de la reserva
     * @return La reserva actualizada
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> actualizarReserva(@Valid @RequestBody ReservaDTO reservaDTO, BindingResult result, @PathVariable Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        if(result.hasErrors()) {
            return validar(result);
        }
        Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
        resService.actualizarReserva(idReserva,reserva);
        ReservaDTO updatedReservaDTO = modelMapper.map(reserva, ReservaDTO.class);
        ApiResponse<ReservaDTO> response = new ApiResponse<>(true, "Reserva actualizada con éxito",updatedReservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Asignar cliente a una reserva.
     *
     * @param id  de la reserva
     * @param id del cliente
     * @return ResponseEntity con un mensaje de éxito
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */


    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
