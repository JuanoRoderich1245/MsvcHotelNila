/*
 * @file DetalleReserva.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:11:50
 */
package org.grupouno.msvcnegocio.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDetalleReserva")
public class DetalleReserva {
    /** Representa el identificador único para la entidad Detalle Reserva. */

    /** The id detalle reserva. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReserva;

    /** Representa la habitacion del detalle reserva. */

    /** The pago reserva. */
    private float pagoReserva;

    /** The estado reserva. */
    private boolean estadoReserva;

    /** The habitacion. */
    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;
    /** Representa la reserva del detalle reserva.*/

    /** The reserva. */
    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;
}
