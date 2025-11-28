package com.main.gym_api.service;

import com.main.gym_api.dto.RutinaDTO;
import java.util.List;

public interface RutinaService {
    RutinaDTO crearRutina(RutinaDTO dto);
    List<RutinaDTO> listarRutinas();
    List<RutinaDTO> listarPorMiembro(Long miembroId);
    List<RutinaDTO> listarPorEquipo(Long equipoId);
    RutinaDTO obtenerPorId(Long id);
    RutinaDTO actualizarRutina(Long id, RutinaDTO dto);
    void eliminarRutina(Long id);
}

