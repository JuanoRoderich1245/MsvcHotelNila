/*
 * @file ComprobanteServiceImp.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,19:55:08
 */
package org.grupouno.msvcnegocio.service;

import jakarta.transaction.Transactional;
import org.grupouno.msvcnegocio.dominio.Comprobante;
import org.grupouno.msvcnegocio.dominio.Reserva;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.ErrorMessage;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;
import org.grupouno.msvcnegocio.repository.ComprobanteRepository;
import org.grupouno.msvcnegocio.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The Class ComprobanteServiceImp.
 */
@Service
public class ComprobanteServiceImp implements ComprobanteService {

    @Autowired
    private ComprobanteRepository facRep;

    @Autowired
    private ReservaRepository resRep;

    /**
     * Listar facturas.
     *
     * @return lista de facturas
     */

    @Override
    @Transactional
    public List<Comprobante> listarComprobantes() {
        return facRep.findAll();
    }

    /**
     * Buscar por id factura.
     *
     * @param idFactura the id factura
     * @return the factura
     * @throws EntityNotFoundException the entity not found exception
     */
    @Override
    @Transactional
    public Comprobante buscarPorIdComprobante(Long idComprobante) throws EntityNotFoundException {
        Optional<Comprobante> facturas = facRep.findById(idComprobante);
        if (facturas.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND);
        }
        return facturas.get();
    }

    /**
     * Crear factura.
     *
     * @param factura the factura
     * @return the factura
     * @throws EntityNotFoundException the entity not found exception
     */
    @Override
    @Transactional
    public Comprobante crearComprobante(Comprobante comprobante) throws IllegalOperationException {
        return facRep.save(comprobante);
    }

    /**
     * Actualizar factura.
     *
     * @param idFactura the id factura
     * @param factura the factura
     * @return the factura
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public Comprobante actualizarComprobante(Long idComprobante, Comprobante comprobante)
            throws EntityNotFoundException, IllegalOperationException {
        Optional<Comprobante> comprobanteEntity = facRep.findById(idComprobante);
        if (comprobanteEntity.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND);
        }
        comprobante.setIdComprobante(idComprobante);
        // TODO Auto-generated method stub
        return facRep.save(comprobante);
    }

    /**
     * Eliminar factura.
     *
     * @param idFactura the id factura
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public void eliminarComprobante(Long idComprobante) throws EntityNotFoundException, IllegalOperationException {
        Comprobante comprobante = facRep.findById(idComprobante).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND)
        );
        facRep.deleteById(idComprobante);
    }

    /**
     * Asignar reserva.
     *
     * @param idFactura the id factura
     * @param idReserva the id reserva
     * @return the factura
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public Comprobante asignarReserva(Long idComprobante, Long idReserva)
            throws EntityNotFoundException, IllegalOperationException{

        Comprobante comprobante = facRep.findById(idComprobante).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.COMPROBANTE_NOT_FOUND)
        );

        Reserva reserva = resRep.findById(idReserva).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.RESERVA_NOT_FOUND)
        );

        // Verificar si la reserva ya tiene un comprobate asignado
        if (reserva.getComprobante() != null) {
            throw new IllegalOperationException("La reserva ya tiene un comprobante asignado.");
        }
        comprobante.setReserva(reserva);
        facRep.save(comprobante);
        return comprobante;

    }
}
