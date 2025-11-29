package com.main.gym_api.service;

import com.main.gym_api.dto.InscripcionDTO;
import java.util.List;

public interface InscripcionService {
    InscripcionDTO crearInscripcion(InscripcionDTO dto);
    List<InscripcionDTO> listarInscripciones();
    List<InscripcionDTO> listarPorMiembro(Long miembroId);
    List<InscripcionDTO> listarPorClase(Long claseId);
    InscripcionDTO obtenerPorId(Long id);
    InscripcionDTO actualizarEstado(Long id, String nuevoEstado);
    void eliminar(Long id);
}

