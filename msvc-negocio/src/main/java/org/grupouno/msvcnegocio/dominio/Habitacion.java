/*
 * @file Habitacion.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:46
 */
package org.grupouno.msvcnegocio.dominio;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/*
 * @file Habitacion.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:08:46
 */
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Habitación.
 */
@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idHabitacion")
public class Habitacion {

    /**
     * Representa el identificador único para la entidad Habitacion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;

    /**
     * El nro de habitacion teniendo como restriccion que no puede ser nula.
     */
    private String nroHabitacion;
    /**
     * El tipo de habitacion teniendo como restriccion que no puede estar en blanco.
     */
    private String tipoHabitacion;
    /**
     * El estado de habitacion teniendo como restriccion que no puede estar en blanco.
     */
    private String estado;
    /**
     * El precio de habitacion teniendo como restriccion que no puede ser nulo.
     */
    private Float precio;

    /**
     * Representa la lista de  detalles reservas realizadas por la habitación.
     */

    @ManyToMany
    private List<Reserva> reserva = new ArrayList<>();

    /**
     * The reserva habitacion.
     */
    @OneToMany(mappedBy = "habitacion")
    //@JsonManagedReference
    private List<DetalleReserva> reserva_habitacion = new ArrayList<>();
}