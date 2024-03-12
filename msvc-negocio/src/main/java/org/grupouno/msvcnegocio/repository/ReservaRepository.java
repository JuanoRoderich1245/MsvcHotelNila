/*
 * @file ReservaRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 3 mar. 2024,10:59:11
 */
package org.grupouno.msvcnegocio.repository;

import org.grupouno.msvcnegocio.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * La interfaz ReservaRepository proporciona métodos para interactuar con la entidad Reserva.
 * The Interface ReservaRepository.
 */
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
