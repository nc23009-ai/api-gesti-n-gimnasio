package com.main.gym_api.service.impl;

import com.main.gym_api.dto.MembresiaDTO;
import com.main.gym_api.dto.MiembroDTO;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.MiembroService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepo;
    private final MembresiaRepository membresiaRepo;

    @Override
    public MiembroDTO crearMiembro(MiembroDTO dto) {
        Miembro miembro = new Miembro();
        // Mapeo campos persona
        miembro.setNombre(dto.getNombre());
        miembro.setApellido(dto.getApellido());
        miembro.setEmail(dto.getEmail());
        miembro.setTelefono(dto.getTelefono());

        miembro.setFechaRegistro(dto.getFechaRegistro());
        miembro.setMiembroActivo(dto.isMiembroActivo());

        // Si DTO incluye membresía embebida, crearla y asignarla
        if (dto.getMembresia() != null) {
            MembresiaDTO mdto = dto.getMembresia();
            Membresia membresia = new Membresia();
            membresia.setTipo(mdto.getTipo());
            membresia.setFechaInicio(mdto.getFechaInicio());
            membresia.setFechaFin(mdto.getFechaFin());
            membresia.setCosto(mdto.getCosto());
            membresia.setActiva(mdto.isActiva());
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
        return miembroRepo.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado con id: " + id));
    }

    @Override
    public MiembroDTO actualizarMiembro(Long id, MiembroDTO dto) {
        Miembro miembroExistente = miembroRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado con id: " + id));

        // Actualizar campos persona
        miembroExistente.setNombre(dto.getNombre());
        miembroExistente.setApellido(dto.getApellido());
        miembroExistente.setEmail(dto.getEmail());
        miembroExistente.setTelefono(dto.getTelefono());

        miembroExistente.setFechaRegistro(dto.getFechaRegistro());
        miembroExistente.setMiembroActivo(dto.isMiembroActivo());

        if (dto.getMembresia() != null) {
            MembresiaDTO mdto = dto.getMembresia();
            Membresia membresiaActualizada = new Membresia();
            if (miembroExistente.getMembresia() != null) {
                membresiaActualizada.setId(miembroExistente.getMembresia().getId());
            }

            membresiaActualizada.setTipo(mdto.getTipo());
            membresiaActualizada.setFechaInicio(mdto.getFechaInicio());
            membresiaActualizada.setFechaFin(mdto.getFechaFin());
            membresiaActualizada.setActiva(mdto.isActiva());
            membresiaActualizada.setCosto(mdto.getCosto());
            membresiaActualizada.setMiembro(miembroExistente);

            miembroExistente.setMembresia(membresiaActualizada);
        } else {
            // si DTO no trae membresia, mantener la existente (o desasociar si lo deseas)
        }

        Miembro miembroGuardado = miembroRepo.save(miembroExistente);

        return mapToDTO(miembroGuardado);
    }

    @Override
    public void eliminarMiembro(Long id) {
        if (!miembroRepo.existsById(id)) throw new EntityNotFoundException("Miembro no encontrado");
        miembroRepo.deleteById(id);
    }

    @Override
    public List<MiembroDTO> obtenerMiembrosActivos() {
        return miembroRepo.findByMiembroActivoTrue().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MiembroDTO cambiarEstadoMiembro(Long id, boolean activo) {
        Miembro miembro = miembroRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));
        miembro.setMiembroActivo(activo);
        return mapToDTO(miembroRepo.save(miembro));
    }

    // --- Helpers ---
    private MiembroDTO mapToDTO(Miembro m) {
        MiembroDTO dto = new MiembroDTO();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setApellido(m.getApellido());
        dto.setEmail(m.getEmail());
        dto.setTelefono(m.getTelefono());
        dto.setFechaRegistro(m.getFechaRegistro());
        dto.setMiembroActivo(m.isMiembroActivo());
        if (m.getMembresia() != null) {
            dto.setMembresia(mapToDTO(m.getMembresia()));
        }
        return dto;
    }

    private MembresiaDTO mapToDTO(Membresia e) {
        if (e == null) return null;
        MembresiaDTO dto = new MembresiaDTO();
        dto.setId(e.getId());
        dto.setTipo(e.getTipo());
        dto.setFechaInicio(e.getFechaInicio());
        dto.setFechaFin(e.getFechaFin());
        dto.setCosto(e.getCosto());
        dto.setActiva(e.isActiva());
        dto.setMiembroId(e.getMiembro() != null ? e.getMiembro().getId() : null);
        return dto;
    }
}
