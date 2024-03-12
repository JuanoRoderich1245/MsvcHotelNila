/*
 * @file HabitacionDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:05
 */
package org.grupouno.msvcnegocio.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.grupouno.msvcnegocio.dominio.DetalleReserva;
import org.grupouno.msvcnegocio.dominio.Reserva;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * La clase HabitacionDTO.
 * The Class HabitacionDTO.
 */

public class HabitacionDTO {
    /** Representa el identificador único para la entidad Habitacion. */
    @Id
    private Long idHabitacion;

    /** El nro de habitacion teniendo como restriccion que no puede ser nula. */
    @NotNull(message = "El número de habitación no puede ser nula")
    private String nroHabitacion;
    /** El tipo de habitacion teniendo como restriccion que no puede estar en blanco. */
    @NotBlank(message = "Tipo de habitación no pueden estar en blanco")
    private String tipoHabitacion;
    /** El estado de habitacion teniendo como restriccion que no puede estar en blanco. */
    @NotBlank(message = "Estado de habitación no pueden estar en blanco")
    private String estado;
    /** El precio de habitacion teniendo como restriccion que no puede ser nulo. */
    @NotNull(message = "El precio de habitación no puede ser nula")
    private Float precio;
    private List<Reserva> reserva = new ArrayList<>();
    private List<DetalleReserva> reserva_habitacion = new ArrayList<>();
}
