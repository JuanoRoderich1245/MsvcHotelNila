/*
 * @file DireccionDTO.java;
 * @Autor (c)2024 JhenniferMendoza
 * @Created 3 mar. 2024,11:27:52
 */
package org.grupouno.msvccliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.grupouno.msvccliente.dominio.Cliente;

@Data
public class DireccionDTO {
    /**  Representa el identificador único de la dirección */
    private Long idDireccion;

    /** Representa el nombre o descripción de la dirección. Limitado a un máximo de 50 caracteres.*/
    @NotBlank
    @Size(max=50)
    private String nombreDirec;

    /** Representa la ciudad en la que se encuentra la dirección. */
    @Size(max=30)
    private String ciudad;

    /**Representa el cliente asociado a esta dirección. */
    private Cliente cliente;
}
