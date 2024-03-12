/*
 * @file ComprobanteService.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,19:14:05
 */
package org.grupouno.msvcnegocio.service;

// TODO: Auto-generated Javadoc

import org.grupouno.msvcnegocio.dominio.Comprobante;
import org.grupouno.msvcnegocio.exception.EntityNotFoundException;
import org.grupouno.msvcnegocio.exception.IllegalOperationException;

import java.util.List;
/**
 * The Interface ComprobanteService.
 */
public interface ComprobanteService {
    /**
     * Lista todos los comprobantes registrados.
     *
     * @return Lista de comprobantes
     */
    List<Comprobante> listarComprobantes();

    /**
     /**
     * Busca un comprobante por su ID.
     *
     * @param IdComprobante ID del comprobante a buscar
     * @return Comprobante encontrado
     * @throws EntityNotFoundException
     */
    Comprobante buscarPorIdComprobante(Long idComprobante) throws EntityNotFoundException;

    /**
     * Crea un nuevo comprobante
     *
     * @param Comprobante a ser creado
     * @return El comprobante creado
     * @throws IllegalOperationException
     */

    Comprobante crearComprobante(Comprobante comprobante) throws IllegalOperationException;

    /**
     ** Actualiza la información de comprobante existente.
     *
     * @param  Comprobante a actualizar
     * @param  Información actualizada del comprobante
     * @return El comprobante actualizado
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */

    Comprobante actualizarComprobante(Long idComprobante, Comprobante comprobante)throws EntityNotFoundException, IllegalOperationException;

    /**
     * Elimina un comprobante.
     *
     * @param  Id del comprobante a eliminar
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */

    void eliminarComprobante(Long idComprobante)throws EntityNotFoundException, IllegalOperationException;

    /**
     /**
     * Asigna una reserva a un comprobante.
     *
     * @param ID del comprobante al que se le asignará la reserva
     * @param ID de la reserva a asignar
     * @return comprobante con la reserva asignada
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */

    Comprobante asignarReserva(Long idComprobante, Long idReserva)throws EntityNotFoundException, IllegalOperationException;
}
