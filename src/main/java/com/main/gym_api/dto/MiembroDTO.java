package com.main.gym_api.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MiembroDTO {
    private Long id;
    private Date fechaRegistro;
    private boolean miembroActivo;
    private MembresiaDTO membresia;
}