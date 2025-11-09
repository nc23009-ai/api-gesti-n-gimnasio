package com.main.gym_api.service.impl;

import com.main.gym_api.dto.*;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.MiembroService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepo;
    private final MembresiaRepository membresiaRepo;

    @Override
    public MiembroDTO crearMiembro(MiembroDTO dto) {
        Miembro miembro = new Miembro();
        miembro.setFechaRegistro(dto.getFechaRegistro());
        miembro.setMiembroActivo(dto.isMiembroActivo());
        if (dto.getMembresia() != null) {
            Membresia membresia = new Membresia();
            membresia.setTipo(dto.getMembresia().getTipo());
            membresia.setFechaInicio(dto.getMembresia().getFechaInicio());
            membresia.setFechaFin(dto.getMembresia().getFechaFin());
            membresia.setActiva(dto.getMembresia().isActiva());
            membresia.setCosto(dto.getMembresia().getCosto());

            membresia.setMiembro(miembro);
            miembro.setMembresia(membresia);
        }

        Miembro miembroGuardado = miembroRepo.save(miembro);
        miembroRepo.flush();

        return mapToDTO(miembroGuardado);
    }

    @Override
    public List<MiembroDTO> obtenerTodosLosMiembros() {
        return miembroRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MiembroDTO obtenerMiembroPorId(Long id) {
        return miembroRepo.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public MiembroDTO actualizarMiembro(Long id, MiembroDTO dto) {
        Miembro miembroExistente = miembroRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id));

        miembroExistente.setFechaRegistro(dto.getFechaRegistro());
        miembroExistente.setMiembroActivo(dto.isMiembroActivo());

        if (dto.getMembresia() != null) {
            Membresia membresiaActualizada = new Membresia();

            if (miembroExistente.getMembresia() != null) {
                membresiaActualizada.setId(miembroExistente.getMembresia().getId());
            }

            membresiaActualizada.setTipo(dto.getMembresia().getTipo());
            membresiaActualizada.setFechaInicio(dto.getMembresia().getFechaInicio());
            membresiaActualizada.setFechaFin(dto.getMembresia().getFechaFin());
            membresiaActualizada.setActiva(dto.getMembresia().isActiva());
            membresiaActualizada.setCosto(dto.getMembresia().getCosto());

            membresiaActualizada.setMiembro(miembroExistente);
            miembroExistente.setMembresia(membresiaActualizada);
        }

        Miembro miembroGuardado = miembroRepo.save(miembroExistente);

        MiembroDTO respuesta = new MiembroDTO();
        respuesta.setId(miembroGuardado.getId());
        respuesta.setFechaRegistro(miembroGuardado.getFechaRegistro());
        respuesta.setMiembroActivo(miembroGuardado.isMiembroActivo());

        if (miembroGuardado.getMembresia() != null) {
            MembresiaDTO mDTO = new MembresiaDTO();
            mDTO.setId(miembroGuardado.getMembresia().getId());
            mDTO.setTipo(miembroGuardado.getMembresia().getTipo());
            mDTO.setFechaInicio(miembroGuardado.getMembresia().getFechaInicio());
            mDTO.setFechaFin(miembroGuardado.getMembresia().getFechaFin());
            mDTO.setActiva(miembroGuardado.getMembresia().isActiva());
            mDTO.setCosto(miembroGuardado.getMembresia().getCosto());
            respuesta.setMembresia(mDTO);
        }

        return respuesta;

    }

    @Override
    public void eliminarMiembro(Long id) {
        miembroRepo.deleteById(id);
    }

    @Override
    public List<MiembroDTO> obtenerMiembrosActivos() {
        return miembroRepo.findByMiembroActivoTrue().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MiembroDTO cambiarEstadoMiembro(Long id, boolean activo) {
        Miembro miembro = miembroRepo.findById(id).orElseThrow();
        miembro.setMiembroActivo(activo);
        return mapToDTO(miembroRepo.save(miembro));
    }

    // --- Helpers ---
    private MiembroDTO mapToDTO(Miembro m) {
        MiembroDTO dto = new MiembroDTO();
        dto.setId(m.getId());
        dto.setFechaRegistro(m.getFechaRegistro());
        dto.setMiembroActivo(m.isMiembroActivo());
        if (m.getMembresia() != null)
            dto.setMembresia(mapToDTO(m.getMembresia()));
        return dto;
    }

    private MembresiaDTO mapToDTO(Membresia e) {
        MembresiaDTO dto = new MembresiaDTO();
        dto.setId(e.getId());
        dto.setTipo(e.getTipo());
        dto.setFechaInicio(e.getFechaInicio());
        dto.setFechaFin(e.getFechaFin());
        dto.setCosto(e.getCosto());
        dto.setActiva(e.isActiva());
        return dto;
    }

    private Membresia mapToEntity(MembresiaDTO dto) {
        Membresia e = new Membresia();
        e.setTipo(dto.getTipo());
        e.setId(dto.getId());
        e.setFechaInicio(dto.getFechaInicio());
        e.setFechaFin(dto.getFechaFin());
        e.setCosto(dto.getCosto());
        e.setActiva(dto.isActiva());
        return e;
    }
}
