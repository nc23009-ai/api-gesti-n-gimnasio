package com.main.gym_api.service.impl;

import com.main.gym_api.dto.ClaseDTO;
import com.main.gym_api.model.Clase;
import com.main.gym_api.model.Empleado;
import com.main.gym_api.repository.ClaseRepository;
import com.main.gym_api.repository.EmpleadoRepository;
import com.main.gym_api.repository.InscripcionRepository;
import com.main.gym_api.service.ClaseService;
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
public class ClaseServiceImpl implements ClaseService {

    private final ClaseRepository claseRepository;
    private final InscripcionRepository inscripcionRepository;
    private final EmpleadoRepository empleadoRepository;

    @Override
    public ClaseDTO crearClase(ClaseDTO dto) {
        Clase c = EntityMapper.toClaseEntity(dto);

        // Asignar empleado si viene empleadoId
        if (dto.getEmpleadoId() != null) {
            Empleado emp = empleadoRepository.findById(dto.getEmpleadoId())
                    .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
            c.setEmpleado(emp);
        }

        Clase saved = claseRepository.save(c);
        return EntityMapper.toClaseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaseDTO> listarClases() {
        return claseRepository.findAll().stream().map(EntityMapper::toClaseDTO).collect(Collectors.toList());
    }

    @Override
    public ClaseDTO obtenerPorId(Long id) {
        Clase c = claseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));
        return EntityMapper.toClaseDTO(c);
    }

    @Override
    public ClaseDTO actualizarClase(Long id, ClaseDTO dto) {
        Clase c = claseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));
        c.setNombre(dto.getNombre());
        c.setFecha(dto.getFecha());
        c.setCupoMaximo(dto.getCupoMaximo());

        if (dto.getEmpleadoId() != null) {
            Empleado emp = empleadoRepository.findById(dto.getEmpleadoId())
                    .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
            c.setEmpleado(emp);
        } else {
            c.setEmpleado(null);
        }

        Clase saved = claseRepository.save(c);
        return EntityMapper.toClaseDTO(saved);
    }

    @Override
    public void eliminarClase(Long id) {
        if (!claseRepository.existsById(id)) throw new EntityNotFoundException("Clase no encontrada");
        claseRepository.deleteById(id);
    }

    @Override
    public int contarInscritos(Long claseId) {
        return inscripcionRepository.countByClaseIdAndEstadoInscripcion(claseId, com.main.gym_api.model.EstadoInscripcion.INSCRITO);
    }
}
