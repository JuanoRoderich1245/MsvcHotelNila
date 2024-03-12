/*
 * @file Reserva.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:31
 */
package org.grupouno.msvcnegocio.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.grupouno.msvcempleado.dominio.Recepcionista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La clase Reserva.
 * The Class Reserva.
 */

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    /** Representa la fecha de inicio. */
    private Date fechaInicio;

    /** Representa la fecha de fin. */
    private Date fechaFin;

    @ManyToOne
    //@JsonBackReference
    private Recepcionista recepcionista;

    /** Representa la reserva del cliente. */
    /** The cliente. */



    /** Representa la reserva del comprobante. */
    /** The comprobante. */
    @OneToOne
    //@JsonManagedReference
    private Comprobante comprobante;

    /** Representa la lista de detalle reserva realizado por las reservas. */
    /** The reserva habitacion. */
    @OneToMany(mappedBy = "reserva")
    //@JsonManagedReference
    private List<DetalleReserva> reserva_habitacion = new ArrayList<>();

}
