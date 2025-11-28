package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutinaDTO {
    private Long id;

    @NotBlank
    private String descripcion;

    @Min(1)
    private int duracionSemanas;

    @Min(0)
    private int repsEstandar;

    private LocalDate fechaInicio;

    @NotNull
    private Long miembroId;

    @NotNull
    private Long equipoId;
}
