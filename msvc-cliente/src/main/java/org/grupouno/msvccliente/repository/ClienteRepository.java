/*
 * @file ClienteRepository.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:57:24
 */
package org.grupouno.msvccliente.repository;

import org.grupouno.msvccliente.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * La interfaz ClienteRepository proporciona métodos para interactuar con la entidad Cliente
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     *  * Busca clientes por su número de DNI.
     *
     * @param dni del cliente a buscar
     */
    List<Cliente> findBydni(String dniCli);
}
