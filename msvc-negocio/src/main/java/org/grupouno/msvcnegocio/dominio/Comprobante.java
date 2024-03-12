/*
 * @file Comprobante.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:24:58
 */
package org.grupouno.msvcnegocio.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Factura.
 */

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComprobante")
public class Comprobante {
    /** Id de la factura */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;

    /** Fecha en la que se crea el comprobante */
    private Date fechaComprobante;

    /** Hace referencia al estado de pagado o no pagado*/
    private String estado;

    /** Reserva a la que está asociada el comprobante */
    @OneToOne //Relacion de uno a uno
    //@JsonBackReference
    @JoinColumn(name = "res_id")
    private Reserva reserva;
}
