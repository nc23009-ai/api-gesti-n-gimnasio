package com.main.gym_api.service;

import com.main.gym_api.dto.EquipoDTO;
import java.util.List;

public interface EquipoService {
    EquipoDTO crearEquipo(EquipoDTO dto);
    List<EquipoDTO> listarEquipos();
    EquipoDTO obtenerPorId(Long id);
    EquipoDTO actualizarEquipo(Long id, EquipoDTO dto);
    void eliminarEquipo(Long id);
}

