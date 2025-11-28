package com.main.gym_api.service.impl;


import com.main.gym_api.dto.EmpleadoDTO;
import com.main.gym_api.model.Empleado;
import com.main.gym_api.repository.EmpleadoRepository;
import com.main.gym_api.service.EmpleadoService;
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
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTO crearEmpleado(EmpleadoDTO dto) {
        Empleado e = EntityMapper.toEmpleadoEntity(dto);
        Empleado saved = empleadoRepository.save(e);
        return EntityMapper.toEmpleadoDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(EntityMapper::toEmpleadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTO obtenerPorId(Long id) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        return EntityMapper.toEmpleadoDTO(e);
    }

    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO dto) {
        Empleado e = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEmail(dto.getEmail());
        e.setTelefono(dto.getTelefono());
        e.setRole(dto.getRole());
        e.setArea(dto.getArea());
        Empleado saved = empleadoRepository.save(e);
        return EntityMapper.toEmpleadoDTO(saved);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) throw new EntityNotFoundException("Empleado no encontrado");
        empleadoRepository.deleteById(id);
    }
}
