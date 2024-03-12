/*
 * @file DireccionServiceImp.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,12:32:27
 */
package org.grupouno.msvccliente.service;


import jakarta.transaction.Transactional;
import org.grupouno.msvccliente.dominio.Direccion;
import org.grupouno.msvccliente.exception.EntityNotFoundException;
import org.grupouno.msvccliente.exception.ErrorMessage;
import org.grupouno.msvccliente.exception.IllegalOperationException;
import org.grupouno.msvccliente.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The Class DireccionServiceImp.
 */
@Service
public class DireccionServiceImp implements DireccionService {

    /** The direc rep. */
    @Autowired
    private DireccionRepository direcRep;

    /**
     * Listar direcciones.
     *
     * @return the list
     */
    @Override
    @Transactional
    public List<Direccion> listarDirecciones() {
        return  direcRep.findAll();
    }

    /**
     * Buscar por id direccion.
     *
     * @param idDireccion the id direccion
     * @return the direccion
     * @throws EntityNotFoundException the entity not found exception
     */
    @Override
    @Transactional
    public Direccion buscarPorIdDireccion(Long idDireccion) throws EntityNotFoundException {
        Optional<Direccion> direcciones = direcRep.findById(idDireccion);
        if (direcciones.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.DIRECCION_NOT_FOUND);
        }
        return direcciones.get();
    }

    /**
     * Crear direccion.
     *
     * @param direccion the direccion
     * @return the direccion
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public Direccion crearDireccion(Direccion direccion) throws IllegalOperationException {
        if(!direcRep.findBynombreDirec(direccion.getNombreDirec()).isEmpty()){
            throw new IllegalOperationException("La dirección ya está registrada.");
        }
        return direcRep.save(direccion);
    }

    /**
     * Actualizar direccion.
     *
     * @param idDireccion the id direccion
     * @param direccion the direccion
     * @return the direccion
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public Direccion actualizarDireccion(Long idDireccion, Direccion direccion)
            throws EntityNotFoundException, IllegalOperationException {
        Optional<Direccion> direccionEntity = direcRep.findById(idDireccion);
        if(direccionEntity.isEmpty()){
            throw new EntityNotFoundException(ErrorMessage.DIRECCION_NOT_FOUND);
        }
        if(!direcRep.findBynombreDirec(direccion.getNombreDirec()).isEmpty()){
            throw new IllegalOperationException("La dirección ya está registrada.");
        }
        direccion.setIdDireccion(idDireccion);
        return direcRep.save(direccion);
    }

    /**
     * Eliminar direccion.
     *
     * @param idDireccion the id direccion
     * @throws EntityNotFoundException the entity not found exception
     * @throws IllegalOperationException the illegal operation exception
     */
    @Override
    @Transactional
    public void eliminarDireccion(Long idDireccion) throws EntityNotFoundException, IllegalOperationException {
        Direccion direccion = direcRep.findById(idDireccion).orElseThrow(
                ()->new EntityNotFoundException(ErrorMessage.DIRECCION_NOT_FOUND)
        );
        if(!(direccion.getCliente()== null)){
            throw new IllegalOperationException("La dirección esta asociada a un cliente");
        }
        direcRep.deleteById(idDireccion);

    }

}
