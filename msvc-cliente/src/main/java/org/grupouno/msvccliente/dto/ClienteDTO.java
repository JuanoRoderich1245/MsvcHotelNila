/*
 * @file ClienteDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,10:48:14
 */
package org.grupouno.msvccliente.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.grupouno.msvccliente.dominio.Direccion;

@Data
public class ClienteDTO {
    /** Representa el identificador único para la entidad Cliente. */
    private Long idCliente;

    /** Representa el nombre del cliente. Limitado a un máximo de 30 caracteres */
    @Size(max=30)
    @NotBlank
    private String nombreCli;

    /** Representa el apellido paterno del cliente. Limitado a un máximo de 15 caracteres */
    @Size(max=15)
    private String apePat;

    /** Representa el apellido materno del cliente. Limitado a un máximo de 15 caracteres */
    @Size(max=15)
    private String apeMat;

    /** Representa el DNI del cliente. Limitado a 8 dígitos */

    @Column(unique=true)
    @Digits(integer = 8, fraction = 0)
    private String dni;

    /** Representa el número de teléfono del cliente.Limitado 9 dígitos.*/

    @Digits(integer =9, fraction = 0)
    private String telefono;

    /**  Representa la dirección de correo electrónico del cliente */
    @Email(message = "El formato del email es incorrec")
    private String email;

    /** Representa la dirección del cliente.  */
    private Direccion direc;

    /** Representa la lista de reservas realizadas por el cliente */

}
