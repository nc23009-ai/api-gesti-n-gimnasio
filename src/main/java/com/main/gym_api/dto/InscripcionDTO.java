package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO {
    private Long id;

    private LocalDate fechaInscripcion;

    private String estadoInscripcion;

    @NotNull
    private Long miembroId;

    @NotNull
    private Long claseId;
}
