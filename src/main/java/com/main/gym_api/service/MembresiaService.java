package com.main.gym_api.service;

import com.main.gym_api.dto.MembresiaDTO;
import java.util.List;

public interface MembresiaService {
    MembresiaDTO crearMembresia(MembresiaDTO membresiaDTO);
    List<MembresiaDTO> obtenerTodasLasMembresias();
    MembresiaDTO obtenerMembresiaPorId(Long id);
    List<MembresiaDTO> obtenerMembresiasPorMiembro(Long miembroId);
    List<MembresiaDTO> obtenerMembresiasActivasPorMiembro(Long miembroId);
    MembresiaDTO actualizarMembresia(Long id, MembresiaDTO membresiaDTO);
    void eliminarMembresia(Long id);
    MembresiaDTO cambiarEstadoMembresia(Long id, boolean activa);
}
