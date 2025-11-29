package com.main.gym_api.service;

import com.main.gym_api.dto.ClaseDTO;
import java.util.List;

public interface ClaseService {
    ClaseDTO crearClase(ClaseDTO dto);
    List<ClaseDTO> listarClases();
    ClaseDTO obtenerPorId(Long id);
    ClaseDTO actualizarClase(Long id, ClaseDTO dto);
    void eliminarClase(Long id);
    int contarInscritos(Long claseId);
}
