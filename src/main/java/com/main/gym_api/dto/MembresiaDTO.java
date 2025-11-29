package com.main.gym_api.dto;

import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembresiaDTO {
    private Long id;

    @NotBlank(message = "Tipo es obligatorio")
    private String tipo;

    @NotNull(message = "Fecha inicio es obligatoria")
    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Positive(message = "Costo debe ser mayor a 0")
    private double costo;

    private boolean activa;

    // Permite asignar la membresía a un miembro existente
    private Long miembroId;
}

