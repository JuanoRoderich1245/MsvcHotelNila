/*
 * @file ReservaServiceImp.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:49
 */
package org.grupouno.msvcnegocio.service;

import org.grupouno.msvcnegocio.clients.ReservaClienteRest;
import org.grupouno.msvcnegocio.dominio.Reserva;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.ErrorMessage;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.repository.HabitacionRepository;
import org.grupouno.msvcnegocio.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La clase ReservaServiceImp.
 */

@Service
public class ReservaServiceImpl implements ReservaService {

    /** The res rep. */
    @Autowired
    private ReservaRepository resRep;

    /** Para el OpenFeign*/
    @Autowired
    private ReservaClienteRest resclient;

    /** The habi rep. */
    @Autowired
    private HabitacionRepository habiRep;


    /**
     * Listado de las reservas registradas.
     *
     * @return la lista de reservas.
     */
    @Override
    public List<Reserva> listarReservas() {
        return resRep.findAll();
    }


    /**
     * Buscar por id reserva.
     * @param idReserva El ID de la reserva a buscar.
     * @return la reserva encontrada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */
    @Override
    public Reserva buscarPorIdReserva(Long idReserva) throws EntityNotFoundException {
        Optional<Reserva> reservas = resRep.findById(idReserva);
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
        }
        return reservas.get();
    }

    /**
     * Crear reserva.
     * @param reserva La reserva a ser creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public Reserva crearReserva(Reserva reserva) throws IllegalOperationException {
        return resRep.save(reserva);
    }

    /**
     * Actualizar reserva.
     * @param idReserva El ID de la reserva a buscar.
     * @param reserva La reserva a ser actualizada.
     * @return la reserva actualizada.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public Reserva actualizarReserva(Long idReserva, Reserva reserva) throws EntityNotFoundException, IllegalOperationException {
        Optional<Reserva> reservaEntity = resRep.findById(idReserva);
        if(reservaEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND);
        }

        reserva.setIdReserva(idReserva);
        return resRep.save(reserva);
    }


}
