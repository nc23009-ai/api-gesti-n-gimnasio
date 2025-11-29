package com.main.gym_api.service.impl;

import com.main.gym_api.dto.AsistenciaDTO;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.AsistenciaService;
import com.main.gym_api.service.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final MiembroRepository miembroRepository;
    private final ClaseRepository claseRepository;
    private final InscripcionRepository inscripcionRepository;

    @Override
    public AsistenciaDTO registrarAsistencia(AsistenciaDTO dto) {
        Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));
        Clase clase = claseRepository.findById(dto.getClaseId())
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

        // Validar que existe una inscripción con estado INSCRITO
        boolean estaInscrito = inscripcionRepository.findByMiembroIdAndClaseId(dto.getMiembroId(), dto.getClaseId())
                .stream()
                .anyMatch(ins -> ins.getEstadoInscripcion() == EstadoInscripcion.INSCRITO);

        if (!estaInscrito) {
            throw new IllegalStateException("El miembro no está inscrito en la clase (estado INSCRITO requerido)");
        }

        Asistencia a = new Asistencia();
        LocalDateTime fecha = dto.getFechaHora() != null ? dto.getFechaHora() : LocalDateTime.now();
        a.setFechaHora(fecha);
        a.setPresente(dto.isPresente());
        a.setMiembro(miembro);
        a.setClase(clase);

        Asistencia saved = asistenciaRepository.save(a);
        return EntityMapper.toAsistenciaDTO(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AsistenciaDTO> listarTodas() {
        return asistenciaRepository.findAll().stream().map(EntityMapper::toAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> listarPorMiembro(Long miembroId) {
        return asistenciaRepository.findByMiembroId(miembroId).stream().map(EntityMapper::toAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public List<AsistenciaDTO> listarPorClase(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId).stream().map(EntityMapper::toAsistenciaDTO).collect(Collectors.toList());
    }

    @Override
    public AsistenciaDTO obtenerPorId(Long id) {
        Asistencia a = asistenciaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Asistencia no encontrada"));
        return EntityMapper.toAsistenciaDTO(a);
    }

    @Override
    public void eliminar(Long id) {
        if (!asistenciaRepository.existsById(id)) throw new EntityNotFoundException("Asistencia no encontrada");
        asistenciaRepository.deleteById(id);
    }
}
