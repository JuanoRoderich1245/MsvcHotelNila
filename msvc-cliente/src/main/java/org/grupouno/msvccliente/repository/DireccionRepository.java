/*
 * @file DireccionRepository.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:57:18
 */
package org.grupouno.msvccliente.repository;

import org.grupouno.msvccliente.dominio.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * La interfaz HabitacionRepository proporciona métodos para interactuar con la entidad Habitacion.
 */
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    /**
     * Busca direcciones por su nombre.
     * @param nombreDireccion, nombre de la dirección a buscar
     */
    List<Direccion> findBynombreDirec(String nombreDireccion);
}
