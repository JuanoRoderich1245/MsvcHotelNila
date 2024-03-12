/*
 * @file HabitacionRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:05:24
 */
package org.grupouno.msvcnegocio.repository;

import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//TODO: Auto-generated Javadoc
/**
 * La interfaz HabitacionRepository proporciona métodos para interactuar con la entidad Habitacion.
 * The Interface HabitacionRepository.
 */

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    /**
     *
     * @param nroHabitacion, numero de la habitacion a buscar.
     * @param nroHabitacion the nro habitacion
     * @return the list
     */
    List<Habitacion> findBynroHabitacion(String nroHabitacion);
}
