/*
 * @file RecepcionistaService.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:20:59
 */

package org.grupouno.msvcempleado.service;

import jakarta.persistence.EntityNotFoundException;
import org.grupouno.msvcempleado.dominio.Recepcionista;
import org.grupouno.msvcempleado.exception.IllegalOperationException;

import java.util.List;

public interface RecepcionistaService {
    /**
     * Lista todos los recepcionistas registrados.
     *
     * @return Lista de recepcionistas
     */
    List<Recepcionista> listarRecepcionistas();

    /**
     * Busca un recepcionista por su ID.
     *
     * @param idRecepcionista ID del recepcionista a buscar
     * @return El recepcionista encontrado
     * @throws EntityNotFoundException
     */
    Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException;

    /**
     * Crea un nuevo recepcionista.
     *
     * @param  El recepcionista a ser creado
     * @return El recepcionista creado
     * @throws IllegalOperationException
     */
    Recepcionista crearRecepcionista (Recepcionista recepcionista) throws IllegalOperationException;

    /**
     ** Actualiza la información de un recepcionista existente.
     *
     * @param  Id del recepcionista a actualizar
     * @param  Información actualizada del recepcionista
     * @return El recepcionista actualizado
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    Recepcionista actualizarRecepcionista(Long idRecepcionista, Recepcionista recepcionista) throws EntityNotFoundException, IllegalOperationException;

    /**
     * Elimina un recepcionista.
     *
     * @param  Id del recepcionista a eliminar
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    void eliminarRecepcionista(Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException;

}
