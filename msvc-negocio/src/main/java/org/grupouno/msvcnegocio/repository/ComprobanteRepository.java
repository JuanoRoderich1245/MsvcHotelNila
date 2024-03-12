
/*
 * @file ComprobanteRepository.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:25:28
 */
package org.grupouno.msvcnegocio.repository;

import org.grupouno.msvcnegocio.dominio.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface FacturaRepository.
 */

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
