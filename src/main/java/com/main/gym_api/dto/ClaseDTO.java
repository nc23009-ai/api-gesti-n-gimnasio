package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaseDTO {
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private LocalDateTime fecha;

    @Min(1)
    private int cupoMaximo;

    private Long empleadoId;
}

