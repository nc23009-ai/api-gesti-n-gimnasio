package com.main.gym_api.service.impl;

import com.main.gym_api.dto.InscripcionDTO;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.InscripcionService;
import com.main.gym_api.service.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final MiembroRepository miembroRepository;
    private final ClaseRepository claseRepository;

    @Override
    public InscripcionDTO crearInscripcion(InscripcionDTO dto) {
        // Validar miembro y clase
        Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));
        Clase clase = claseRepository.findById(dto.getClaseId())
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

        // Validar cupo
        int inscritos = inscripcionRepository.countByClaseIdAndEstadoInscripcion(clase.getId(), EstadoInscripcion.INSCRITO);
        if (inscritos >= clase.getCupoMaximo()) {
            throw new IllegalStateException("Cupo máximo alcanzado para la clase");
        }

        Inscripcion ins = new Inscripcion();
        ins.setFechaInscripcion(LocalDate.now());
        ins.setEstadoInscripcion(EstadoInscripcion.INSCRITO);
        ins.setMiembro(miembro);
        ins.setClase(clase);

        Inscripcion saved = inscripcionRepository.save(ins);
        return EntityMapper.toInscripcionDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionDTO> listarInscripciones() {
        return inscripcionRepository.findAll().stream().map(EntityMapper::toInscripcionDTO).collect(Collectors.toList());
    }

    @Override
    public List<InscripcionDTO> listarPorMiembro(Long miembroId) {
        return inscripcionRepository.findByMiembroId(miembroId).stream().map(EntityMapper::toInscripcionDTO).collect(Collectors.toList());
    }

    @Override
    public List<InscripcionDTO> listarPorClase(Long claseId) {
        return inscripcionRepository.findByClaseId(claseId).stream().map(EntityMapper::toInscripcionDTO).collect(Collectors.toList());
    }

    @Override
    public InscripcionDTO obtenerPorId(Long id) {
        Inscripcion ins = inscripcionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inscripción no encontrada"));
        return EntityMapper.toInscripcionDTO(ins);
    }

    @Override
    public InscripcionDTO actualizarEstado(Long id, String nuevoEstado) {
        Inscripcion ins = inscripcionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inscripción no encontrada"));
        EstadoInscripcion estado = EstadoInscripcion.valueOf(nuevoEstado);
        ins.setEstadoInscripcion(estado);
        Inscripcion saved = inscripcionRepository.save(ins);
        return EntityMapper.toInscripcionDTO(saved);
    }

    @Override
    public void eliminar(Long id) {
        if (!inscripcionRepository.existsById(id)) throw new EntityNotFoundException("Inscripción no encontrada");
        inscripcionRepository.deleteById(id);
    }
}

