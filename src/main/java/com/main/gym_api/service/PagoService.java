package com.main.gym_api.service;

import com.main.gym_api.dto.PagoDTO;
import java.util.List;

public interface PagoService {
    PagoDTO registrarPago(PagoDTO dto);
    List<PagoDTO> listarTodos();
    List<PagoDTO> listarPorMiembro(Long miembroId);
    List<PagoDTO> listarPorMembresia(Long membresiaId);
    PagoDTO obtenerPorId(Long id);
    void eliminar(Long id);
}

