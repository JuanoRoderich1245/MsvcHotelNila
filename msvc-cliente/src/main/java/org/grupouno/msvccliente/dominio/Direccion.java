/*
 * @file Direccion.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 2 mar. 2024,17:18:48
 */
package org.grupouno.msvccliente.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

/**
 *  Clase que representa una dirección en la persistencia.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDireccion")
public class Direccion {

    /**
     * Representa el identificador único de la dirección
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    /**
     * Representa el nombre o descripción de la dirección.
     */
    private String nombreDirec;

    /**
     * Representa la ciudad en la que se encuentra la dirección.
     */
    private String ciudad;

    /**
     * Representa el cliente asociado a esta dirección.
     */
    @OneToOne
    //@JsonBackReference
    private Cliente cliente;
}

