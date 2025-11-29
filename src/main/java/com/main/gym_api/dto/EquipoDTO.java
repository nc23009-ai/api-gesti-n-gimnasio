package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoDTO {
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String estadoEquipo; // BUEN_ESTADO, MAL_ESTADO, FUERA_DE_USO
}

