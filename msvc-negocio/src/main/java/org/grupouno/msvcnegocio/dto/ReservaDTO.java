/*
 * @file ReservaDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,03:09:13
 */
package org.grupouno.msvcnegocio.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.grupouno.msvcempleado.dominio.Recepcionista;
import org.grupouno.msvcnegocio.dominio.Comprobante;
import org.grupouno.msvcnegocio.dominio.Habitacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La clase ReservaDTO.
 * The Class ReservaDTO.
 */

@Data
public class ReservaDTO {

    /** The id reserva. */
    private Long idReserva;

    /** Se presenta la restricción la fecha de inicio. */
    @Temporal(TemporalType.DATE)
    @FutureOrPresent(message = "La fecha no puede ser anterior a la fecha actual")
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private Date fechaInicio;

    /** Se presenta la restricción la fecha de fin. */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha de fin no puede ser nula")
    private Date fechaFin;

    /** Las habitaciones. */
    private List<Habitacion> habitaciones = new ArrayList<>();

    /** El recepcionista. */
    private Recepcionista recepcionista;

    /** El cliente. */

    /** The comprobante */
    private Comprobante comprobante;
}
