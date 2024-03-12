/*
 * @file HabitacionServiceImp.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:26
 */
package org.grupouno.msvcnegocio.service;

import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.ErrorMessage;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La clase HabitacionServiceImpl.
 */

@Service
public class HabitacionServiceImpl implements HabitacionService {

    /** Creación de un objeto de tipo HabitacionRepository */
    /** The habi rep. */
    @Autowired
    private HabitacionRepository habiRep;

    /**
     * Listar habitaciones.
     * return La lista de habitaciones.
     * @return the list
     */
    @Override
    public List<Habitacion> listarHabitaciones() {
        return (List<Habitacion>) habiRep.findAll();
    }

    /**
     * Buscar por id habitacion.
     * @param idHabitacion El ID de la habitacion a buscar.
     * @return La habitacion encontrada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */
    @Override
    public Habitacion buscarPorIdHabitacion(Long idHabitacion) throws EntityNotFoundException {
        Optional<Habitacion> habitaciones = habiRep.findById(idHabitacion);
        if (habitaciones.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
        }
        return habitaciones.get();
    }

    /**
     * Crear habitacion.
     * @param habitacion La habitacion a ser creada.
     * @return La habitacion creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public Habitacion crearHabitacion(Habitacion habitacion) throws IllegalOperationException {
        if(!habiRep.findBynroHabitacion(habitacion.getNroHabitacion()).isEmpty()) {
            throw new IllegalOperationException("La habitación ya está registrada.");
        }
        return habiRep.save(habitacion);
    }

    /**
     * Actualizar habitacion.
     * @param idHabitacion El id habitacion para ser encontrada.
     * @param habitacion La habitacion para ser actualizada..
     * @return La habitacion actualizada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public Habitacion actualizarHabitacion(Long idHabitacion, Habitacion habitacion) throws EntityNotFoundException, IllegalOperationException {
        Optional<Habitacion> habiEntity = habiRep.findById(idHabitacion);
        if(habiEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND);
        }
        if(!habiRep.findBynroHabitacion(habitacion.getNroHabitacion()).isEmpty()) {
            throw new IllegalOperationException("La habitación ya está registrada.");
        }
        habitacion.setIdHabitacion(idHabitacion);
        return habiRep.save(habitacion);
    }
}
