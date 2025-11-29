package com.main.gym_api.service.impl;

import com.main.gym_api.dto.EquipoDTO;
import com.main.gym_api.model.Equipo;
import com.main.gym_api.model.EstadoEquipo;
import com.main.gym_api.repository.EquipoRepository;
import com.main.gym_api.service.EquipoService;
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
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    @Override
    public EquipoDTO crearEquipo(EquipoDTO dto) {
        Equipo e = new Equipo();
        e.setNombre(dto.getNombre());
        try {
            e.setEstadoEquipo(EstadoEquipo.valueOf(dto.getEstadoEquipo()));
        } catch (Exception ex) {
            e.setEstadoEquipo(EstadoEquipo.BUEN_ESTADO);
        }
        Equipo saved = equipoRepository.save(e);
        return EntityMapper.toEquipoDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> listarEquipos() {
        return equipoRepository.findAll().stream().map(EntityMapper::toEquipoDTO).collect(Collectors.toList());
    }

    @Override
    public EquipoDTO obtenerPorId(Long id) {
        Equipo e = equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
        return EntityMapper.toEquipoDTO(e);
    }

    @Override
    public EquipoDTO actualizarEquipo(Long id, EquipoDTO dto) {
        Equipo e = equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
        e.setNombre(dto.getNombre());
        e.setEstadoEquipo(EstadoEquipo.valueOf(dto.getEstadoEquipo()));
        Equipo saved = equipoRepository.save(e);
        return EntityMapper.toEquipoDTO(saved);
    }

    @Override
    public void eliminarEquipo(Long id) {
        if (!equipoRepository.existsById(id)) throw new EntityNotFoundException("Equipo no encontrado");
        equipoRepository.deleteById(id);
    }
}
