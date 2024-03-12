/*
 * @file RecepcionistaServiceImp.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 5 mar. 2024,00:04:01
 */

package org.grupouno.msvcempleado.service;

import jakarta.persistence.EntityNotFoundException;
import org.grupouno.msvcempleado.dominio.Recepcionista;
import org.grupouno.msvcempleado.exception.ErrorMessage;
import org.grupouno.msvcempleado.exception.IllegalOperationException;
import org.grupouno.msvcempleado.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * La clase RecepcionistaServiceImpl implementa los métodos de la interfaz RecepcionistaService.
 */

@Service
public class RecepcionistaServiceImpl implements RecepcionistaService {

    /** El repositorio del recepcionista. */
    @Autowired
    private RecepcionistaRepository recepcionistaRep;


    /**
     * Listar recepcionistas.
     *
     * @return la lista de recepcionistas
     */
    @Override
    @Transactional
    public List<Recepcionista> listarRecepcionistas() {
        return recepcionistaRep.findAll();
    }

    /**
     * Buscar por id recepcionista.
     *
     * @param idRecepcionista el id del recepcionista
     * @return el recepcionista
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     */
    @Override
    @Transactional
    public Recepcionista buscarPorIdRecepcionista(Long idRecepcionista) throws EntityNotFoundException {
        Optional<Recepcionista> recepcionista = recepcionistaRep.findById(idRecepcionista);
        if (!recepcionista.isPresent()) {
            throw new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND);
        }
        return recepcionista.get();
    }

    /**
     * Crear recepcionista.
     *
     * @param recepcionista el recepcionista a crear
     * @return el recepcionista creado
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
    @Override
    @Transactional
    public Recepcionista crearRecepcionista(Recepcionista recepcionista) throws IllegalOperationException {
        List<Recepcionista> recepcionistasMismoTurno = recepcionistaRep.findByTurno(recepcionista.getTurno());
        if(!recepcionistasMismoTurno.isEmpty()){
            throw new IllegalOperationException("Ya existe un recepcionista asignado para el turno " + recepcionista.getTurno() + ".");
        }
        return recepcionistaRep.save(recepcionista);
    }

    /**
     * Actualizar recepcionista.
     *
     * @param idRecepcionista el id del recepcionista a actualizar
     * @param recepcionista el recepcionista con los datos actualizados
     * @return el recepcionista actualizado
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
    @Override
    @Transactional
    public Recepcionista actualizarRecepcionista(Long idRecepcionista, Recepcionista recepcionista) throws EntityNotFoundException, IllegalOperationException {
        Optional<Recepcionista> recepcionistaEntity = recepcionistaRep.findById(idRecepcionista);
        if(recepcionistaEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.RECEPCIONISTA_NOT_FOUND);
        }
        List<Recepcionista> recepcionistasMismoTurno = recepcionistaRep.findByTurno(recepcionista.getTurno());
        if(!recepcionistasMismoTurno.isEmpty() && !recepcionistasMismoTurno.get(0).getIdRecepcionista().equals(idRecepcionista)){
            throw new IllegalOperationException("Ya existe un recepcionista asignado para el turno " + recepcionista.getTurno() + ".");
        }
        recepcionista.setIdRecepcionista(idRecepcionista);
        return recepcionistaRep.save(recepcionista);
    }
    /**
     * Eliminar recepcionista.
     *
     * @param idRecepcionista el id del recepcionista a eliminar
     * @throws EntityNotFoundException si no se encuentra el recepcionista
     * @throws IllegalOperationException si ocurre una operación ilegal
     */
    @Override
    public void eliminarRecepcionista(Long idRecepcionista) throws EntityNotFoundException, IllegalOperationException {

    }


}
