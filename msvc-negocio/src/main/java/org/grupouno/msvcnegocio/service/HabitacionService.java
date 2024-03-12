/*
 * @file HabitacionService.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:10
 */
package org.grupouno.msvcnegocio.service;

import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;

import java.util.List;

public interface HabitacionService {

    /**
     * Listado de todas las habitaciones registradas.
     *
     * @return La lista de habitaciones.
     */
    List<Habitacion> listarHabitaciones();

    /**
     * Buscar por id habitacion.
     * @param idHabitacion El ID de la habitacion a buscar.
     * @return La habitacion encontrada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */
    Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException;

    /**
     * Crear habitacion.
     * @param habitacion La habitacion a ser creada.
     * @return La habitacion creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    Habitacion crearHabitacion (Habitacion habitacion)throws IllegalOperationException;

    /**
     * Actualizar habitacion.
     * @param idHabitacion El id habitacion para ser encontrada.
     * @param habitacion La habitacion para ser actualizada..
     * @return La habitacion actualizada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException;
}
