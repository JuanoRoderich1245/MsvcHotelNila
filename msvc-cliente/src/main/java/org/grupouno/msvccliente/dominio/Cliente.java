/*
 * @file Cliente.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 2 mar. 2024,17:24:44
 */
package org.grupouno.msvccliente.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;


/**
 * Clase que representa un cliente en la persistencia.
 */
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {

    /** Representa el identificador único para la entidad Cliente. */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCliente;

    /** Representa el nombre del cliente. */
    private String nombreCli;

    /** Representa el apellido paterno del cliente. */
    private String apePat;

    /** Representa el apellido materno del cliente.  */
    private String apeMat;

    /** Representa el DNI del cliente.  */
    private String dni;

    /** Representa el número de teléfono del cliente.*/
    private String telefono;

    /**  Representa la dirección de correo electrónico del cliente */
    private String email;

    /** Representa la dirección del cliente.  */
    @OneToOne(mappedBy = "cliente")
    @JoinColumn(name = "direc_id")
    //@JsonManagedReference
    private Direccion direc;

}
