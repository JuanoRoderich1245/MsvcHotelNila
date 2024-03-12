/*
 * @file DetalleReservaDTO.java;
 * @Autor (c)2024 JuanRuiz
 * @Created 5 mar. 2024,02:10:45
 */
package org.grupouno.msvcnegocio.dto;

import lombok.Data;
import org.grupouno.msvcnegocio.dominio.Habitacion;
import org.grupouno.msvcnegocio.dominio.Reserva;

@Data
public class DetalleReservaDTO {
    /** El id detalle reserva. */
    private Long idDetalleReserva;

    /** El pago reserva. */
    private float pagoReserva;

    /** El estado reserva. */
    private boolean estadoReserva;

    /** El habitacion. */
    private Habitacion habitacion;

    /** The reserva. */
    private Reserva reserva;
}
