/*
 * @file ReservaService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:37
 */
package org.grupouno.msvcnegocio.service;


import org.grupouno.msvcnegocio.dominio.Reserva;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;

import java.util.List;

/**
 * La interfaz ReservaService.
 */
public interface ReservaService {

    /**
     * Listado de las reservas registradas.
     *
     * @return la lista de reservas.
     */
    List<Reserva> listarReservas();

    /**
     * Buscar por id reserva.
     * @param idReserva El ID de la reserva a buscar.
     * @return la reserva encontrada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */
    Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException;

    /**
     * Crear reserva.
     * @param reserva La reserva a ser creada.
     * @return La reserva creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    Reserva crearReserva (Reserva reserva)throws IllegalOperationException;

    /**
     * Actualizar reserva.
     * @param idReserva El ID de la reserva a buscar.
     * @param reserva La reserva a ser actualizada.
     * @return la reserva actualizada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException;

}
