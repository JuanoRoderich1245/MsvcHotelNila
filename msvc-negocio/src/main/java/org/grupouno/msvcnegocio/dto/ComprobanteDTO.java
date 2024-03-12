/*
 * @file ComprobanteDTO.java;
 * @Autor (c)2024 AndersonDietrich
 * @Created 3 mar. 2024,23:15:07
 */
package org.grupouno.msvcnegocio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.grupouno.msvcnegocio.dominio.Reserva;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * La Clase ComprobanteDTO.
 */
@Data
public class ComprobanteDTO {

    /** El id comprobante. */
    private Long idComprobante;
    /** La fecha comprobante. */
    @NotNull(message = "La fecha del comprobante no puede ser nula")
    //@Past(message = "La fecha del comprobante debe estar en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaComprobante;
    //Estado del comprobante
    @Pattern(regexp = "^(Pagado|No pagado)$", message = "El comprobante solo puede tener el estado de Pagado o No pagado")
    private String estado;

    private Reserva reserva;
}
