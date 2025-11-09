package com.main.gym_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MembresiaDTO {
    private Long id;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean activa;
    private Long miembroId;
    private double costo;
}
