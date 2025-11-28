package com.main.gym_api.dto;

import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MiembroDTO {
    private Long id;

    // Campos heredados de Persona (ahora expuestos en DTO)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private String telefono;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    private boolean miembroActivo;

    // Relación 1:1 / historial: incluir id del miembro
    private MembresiaDTO membresia;
}