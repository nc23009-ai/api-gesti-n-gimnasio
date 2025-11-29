package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaDTO {
    private Long id;

    @NotNull
    private LocalDateTime fechaHora;

    private boolean presente;

    @NotNull
    private Long miembroId;

    @NotNull
    private Long claseId;
}
