/*
 * @file DetalleReservaService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:30:20
 */
package org.grupouno.msvcnegocio.service;

import org.grupouno.msvcnegocio.dominio.DetalleReserva;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * La interfaz DetalleReservaService.
 */

public interface DetalleReservaService {

    /**
     * Listado de los detalles reservas.
     * @return la lista de detalles reservados.
     * Listar detalles reservas.
     * @return the list
     */
    List<DetalleReserva> listarDetallesReservas();

    /**
     * Buscar por id detalle reserva.
     * @param idReservaHabitacion El ID del detalle reserva a buscar.
     * @return el detalle reserva encontrado.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */
    DetalleReserva buscarPorIdDetalleReserva(Long idReservaHabitacion) throws EntityNotFoundException;

    /**
     * Crear detalle reserva.
     * @param detalleReserva el detalle reserva para crear
     * @return el detalle reserva creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    DetalleReserva crearDetalleReserva (DetalleReserva detalleReserva)throws IllegalOperationException;

    /**
     * Actualizar detalle reserva.
     * @param idReservaHabitacion El ID de la reservaHabitacion a buscar.
     * @param detalleReserva el detalle reserva para actualizar.
     * @return el detalle reserva actualizado.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    DetalleReserva actualizarDetalleReserva(Long idReservaHabitacion, DetalleReserva detalleReserva) throws EntityNotFoundException, IllegalOperationException;

    /**
     * Asignar reserva.
     * @param idReservaHabitacion el id reserva habitacion por buscar.
     * @param idReserva El id reserva por buscar.
     * @return La reserva asignado a un detalle reserva.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    DetalleReserva asignarReserva(Long idReservaHabitacion, Long idReserva) throws EntityNotFoundException, IllegalOperationException;

    /**
     * Asignar habitacion.
     * @param idReservaHabitacion el id reserva habitacion por buscar.
     * @param idHabitacion El id habitacion por buscar.
     * @return La habitacion asignado a un detalle reserva.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    DetalleReserva asignarHabitacion(Long idReservaHabitacion, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException;
}
