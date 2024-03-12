/*
 * @file Recepcionista.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 2 mar. 2024,22:35:44
 */
package org.grupouno.msvcempleado.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
/**
 * La clase Recepcionista es una entidad en la aplicación que
 * representa a un recepcionista en el dominio del problema.
 *
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRecepcionista")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecepcionista;

    /** Nombre del recepcionista. */
    private String nombre;

    /** Apellido paterno del recepcionista. */
    private String apePat;

    /** Apellido materno del recepcionista. */
    private String apeMat;

    /** Número de telefono del recpcionista. */
    private String telefono;

    /** Turno de trabajo del recepcionista. */
    private String turno;
}
