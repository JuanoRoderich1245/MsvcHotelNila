/*
 * @file DetalleReservaServiceImpl.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:10:49
 */
package org.grupouno.msvcnegocio.service;

import jakarta.transaction.Transactional;
import org.grupouno.msvcnegocio.dominio.DetalleReserva;
import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.grupouno.msvcnegocio.dominio.Reserva;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.ErrorMessage;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.repository.DetalleReservaRepository;
import org.grupouno.msvcnegocio.repository.HabitacionRepository;
import org.grupouno.msvcnegocio.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleReservaServiceImpl implements DetalleReservaService{
    /** Creación de detResRep de tipo DetalleReservaRepository */
    @Autowired
    private DetalleReservaRepository detResRep;

    /** Creación de rsrvRep de tipo ReservaRepository  */
    @Autowired
    private ReservaRepository rsrvRep;

    /** Creación de habiRep de tipo HabitacionRepository   */
    @Autowired
    private HabitacionRepository habRep;

    /**
     * Listado de los detalles reservas.
     * @return la lista de detalles reservados.
     * Listar detalles reservas.
     * @return the list
     */
    @Override
    @Transactional
    public List<DetalleReserva> listarDetallesReservas() {
        return detResRep.findAll();
    }

    /**
     * Buscar por id detalle reserva.
     * @param idReservaHabitacion El ID del detalle reserva a buscar.
     * @return el detalle reserva encontrado.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     */

    @Override
    @Transactional
    public DetalleReserva buscarPorIdDetalleReserva(Long idReservaHabitacion) throws EntityNotFoundException {
        Optional<DetalleReserva> detallereservas = detResRep.findById(idReservaHabitacion);
        if (detallereservas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND);
        }
        return detallereservas.get();
    }

    /**
     * Crear detalle reserva.
     * @param detalleReserva el detalle reserva para crear
     * @return el detalle reserva creada.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public DetalleReserva crearDetalleReserva(DetalleReserva detalleReserva) throws IllegalOperationException {
        return detResRep.save(detalleReserva);
    }

    /**
     * Actualizar detalle reserva.
     *
     * @param idReservaHabitacion El ID de la reservaHabitacion a buscar.
     * @param detalleReserva el detalle reserva para actualizar.
     * @return el detalle reserva actualizado.
     * @throws EntityNotFoundException En caso de que la entidad no exista.
     * @throws IllegalOperationException En caso de que exista una operación mal ejecutada.
     */
    @Override
    public DetalleReserva actualizarDetalleReserva(Long idReservaHabitacion, DetalleReserva detalleReserva) throws EntityNotFoundException, IllegalOperationException {
        Optional<DetalleReserva> detallereservas = detResRep.findById(idReservaHabitacion);
        if(detallereservas.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND);
        }
        detalleReserva.setIdDetalleReserva(detalleReserva.getIdDetalleReserva());
        return detResRep.save(detalleReserva);
    }

    /**
     * Asignar habitacion.
     * @param idReservaHabitacion the id reserva habitacion
     * @param idReserva the id reserva
     * @return the detalle reserva
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    public DetalleReserva asignarReserva(Long idReservaHabitacion, Long idReserva) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detreserva = detResRep.findById(idReservaHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND)
        );

        Reserva reserva = rsrvRep.findById(idReserva).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
        );

        if (reserva.getReserva_habitacion() != null && !reserva.getReserva_habitacion().isEmpty()) {
            throw new IllegalOperationException("El detalle reserva ya tiene una reserva asignada.");
        }

        detreserva.setReserva(reserva);
        detResRep.save(detreserva);
        return detreserva;
    }
/**
 * Asignar habitacion.
 * @param idReservaHabitacion the id reserva habitacion
 * @param idHabitacion the id habitacion
 * @return the detalle reserva
 * @throws EntityNotFoundException the entity not found exception
 * @throws IllegalOperationException the illegal operation exception
 */
    @Override
    public DetalleReserva asignarHabitacion(Long idReservaHabitacion, Long idHabitacion) throws EntityNotFoundException, IllegalOperationException {
        DetalleReserva detReserva = detResRep.findById(idReservaHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DETALLEHABITACION_NOT_FOUND)
        );

        Habitacion habitacion = habRep.findById(idHabitacion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.HABITACION_NOT_FOUND)
        );

        if(habitacion.getReserva_habitacion()!= null && !habitacion.getReserva_habitacion().isEmpty()){
            throw new IllegalOperationException("El detalle reserva ya tiene una habitación asignada.");
        }

        detReserva.setHabitacion(habitacion);
        detResRep.save(detReserva);
        return detReserva;
    }

}
