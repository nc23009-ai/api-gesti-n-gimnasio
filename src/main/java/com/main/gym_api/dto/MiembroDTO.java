package com.main.gym_api.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class MiembroDTO {
    private Long id;
    private LocalDate fechaRegistro;
    private boolean miembroActivo;
    private MembresiaDTO membresia;
}