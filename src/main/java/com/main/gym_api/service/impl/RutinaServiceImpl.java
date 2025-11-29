package com.main.gym_api.service.impl;

import com.main.gym_api.dto.RutinaDTO;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.RutinaService;
import com.main.gym_api.service.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RutinaServiceImpl implements RutinaService {

    private final RutinaRepository rutinaRepository;
    private final MiembroRepository miembroRepository;
    private final EquipoRepository equipoRepository;

    @Override
    public RutinaDTO crearRutina(RutinaDTO dto) {
        Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));
        Equipo equipo = equipoRepository.findById(dto.getEquipoId())
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));

        Rutina r = new Rutina();
        r.setDescripcion(dto.getDescripcion());
        r.setDuracionSemanas(dto.getDuracionSemanas());
        r.setRepsEstandar(dto.getRepsEstandar());
        r.setFechaInicio(dto.getFechaInicio());
        r.setMiembro(miembro);
        r.setEquipo(equipo);

        Rutina saved = rutinaRepository.save(r);
        return EntityMapper.toRutinaDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RutinaDTO> listarRutinas() {
        return rutinaRepository.findAll().stream().map(EntityMapper::toRutinaDTO).collect(Collectors.toList());
    }

    @Override
    public List<RutinaDTO> listarPorMiembro(Long miembroId) {
        return rutinaRepository.findByMiembroId(miembroId).stream().map(EntityMapper::toRutinaDTO).collect(Collectors.toList());
    }

    @Override
    public List<RutinaDTO> listarPorEquipo(Long equipoId) {
        return rutinaRepository.findByEquipoId(equipoId).stream().map(EntityMapper::toRutinaDTO).collect(Collectors.toList());
    }

    @Override
    public RutinaDTO obtenerPorId(Long id) {
        Rutina r = rutinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rutina no encontrada"));
        return EntityMapper.toRutinaDTO(r);
    }

    @Override
    public RutinaDTO actualizarRutina(Long id, RutinaDTO dto) {
        Rutina r = rutinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rutina no encontrada"));
        r.setDescripcion(dto.getDescripcion());
        r.setDuracionSemanas(dto.getDuracionSemanas());
        r.setRepsEstandar(dto.getRepsEstandar());
        r.setFechaInicio(dto.getFechaInicio());

        if (!r.getMiembro().getId().equals(dto.getMiembroId())) {
            Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                    .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));
            r.setMiembro(miembro);
        }
        if (!r.getEquipo().getId().equals(dto.getEquipoId())) {
            Equipo equipo = equipoRepository.findById(dto.getEquipoId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
            r.setEquipo(equipo);
        }

        Rutina saved = rutinaRepository.save(r);
        return EntityMapper.toRutinaDTO(saved);
    }

    @Override
    public void eliminarRutina(Long id) {
        if (!rutinaRepository.existsById(id)) throw new EntityNotFoundException("Rutina no encontrada");
        rutinaRepository.deleteById(id);
    }
}
