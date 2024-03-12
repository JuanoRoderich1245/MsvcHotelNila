/*
 * @file RecepcionistaRepository.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:09:47
 */
package org.grupouno.msvcempleado.repository;

import org.grupouno.msvcempleado.dominio.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * La interfaz ReservaRepository proporciona métodos para interactuar con la entidad Reserva
 */
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long> {

    /**
     *  * Busca recepcionistas por su turno.
     *
     * @param turno del recepcionista a buscar
     */
    List<Recepcionista> findByTurno(String turno);
}
