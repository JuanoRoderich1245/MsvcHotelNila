/*
 * @file DetalleReservaRepository.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:42
 */
package org.grupouno.msvcnegocio.repository;

import org.grupouno.msvcnegocio.dominio.DetalleReserva;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * La interfaz DetalleReservaRepository proporciona métodos para interactuar con la entidad Detalle Reserva.
 * The Interface DetalleReservaRepository.
 */
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva,Long> {
}
