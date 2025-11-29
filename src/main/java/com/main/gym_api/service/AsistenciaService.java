package com.main.gym_api.service;

import com.main.gym_api.dto.AsistenciaDTO;
import java.util.List;

public interface AsistenciaService {
    AsistenciaDTO registrarAsistencia(AsistenciaDTO dto);
    List<AsistenciaDTO> listarTodas();
    List<AsistenciaDTO> listarPorMiembro(Long miembroId);
    List<AsistenciaDTO> listarPorClase(Long claseId);
    AsistenciaDTO obtenerPorId(Long id);
    void eliminar(Long id);
}
