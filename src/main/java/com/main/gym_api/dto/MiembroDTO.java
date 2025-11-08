package com.main.gym_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MiembroDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String fechaRegistro;
    private Boolean estadoActivo;
}

