package com.main.gym_api.dto;

import jakarta.persistence.Table;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inscripciones")
public class InscripcionDTO {
    private Long id;

    private LocalDate fechaInscripcion;

    private String estadoInscripcion;

    @NotNull
    private Long miembroId;

    @NotNull
    private Long claseId;
}
