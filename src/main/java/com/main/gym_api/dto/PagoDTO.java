package com.main.gym_api.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoDTO {
    private Long id;

    @Positive(message = "El monto debe ser mayor que 0")
    private double monto;

    @NotNull
    private LocalDate fechaPago;

    @NotBlank
    private String metodo;

    @NotNull
    private Long miembroId;

    private Long membresiaId;
}

