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
            System.out.println("Membresia: " + dto.getMembresia());
            Membresia membresia = mapToEntity(dto.getMembresia());
            miembro.setMembresia(membresia);
        }

        return mapToDTO(miembroRepo.save(miembro));
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
        Miembro miembro = miembroRepo.findById(id).orElseThrow();
        miembro.setFechaRegistro(dto.getFechaRegistro());
        miembro.setMiembroActivo(dto.isMiembroActivo());
        if (dto.getMembresia() != null) {
            miembro.setMembresia(mapToEntity(dto.getMembresia()));
        }
        return mapToDTO(miembroRepo.save(miembro));
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
        //dto.setId(e.getId());
        dto.setTipo(e.getTipo());
        dto.setFechaInicio(e.getFechaInicio());
        dto.setFechaFin(e.getFechaFin());
        dto.setCosto(e.getCosto());
        dto.setActiva(e.isActiva());
        return dto;
    }

    private Membresia mapToEntity(MembresiaDTO dto) {
        Membresia e = new Membresia();
        //e.setId(dto.getId());
        e.setTipo(dto.getTipo());
        e.setFechaInicio(dto.getFechaInicio());
        e.setFechaFin(dto.getFechaFin());
        e.setCosto(dto.getCosto());
        e.setActiva(dto.isActiva());
        return e;
    }
}
