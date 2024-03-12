/*
 * @file RecepcionistaDTO.java;
 * @Autor (c)2024 ErickBecerra
 * @Created 3 mar. 2024,16:14:36
 */

package org.grupouno.msvcempleado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
// TODO: Auto-generated Javadoc
/**
 * The Class RecepcionistaDTO.
 */
@Data
public class RecepcionistaDTO {

    private Long idRecepcionista;
    /** Nombre del recepcionista. */
    @Size(max = 30)
    @NotBlank
    private String nombre;

    /** Apellido paterno del recepcionista. */
    @Size(max = 15)
    @NotBlank
    private String apePat;

    /** Apellido materno del recepcionista. */
    @Size(max = 15)
    @NotBlank
    private String apeMat;

    /** Número de telefono del recpcionista. */
    @NotBlank(message = "El campo no puede estar vacío")
    private String telefono;

    /** Turno de trabajo del recepcionista. */
    @NotBlank
    @Pattern(regexp = "^(Mañana|Tarde)$", message = "Solo existe turno Mañana y Tarde")
    private String turno;
}
