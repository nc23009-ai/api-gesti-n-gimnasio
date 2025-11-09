package com.main.gym_api.service.impl;

import com.main.gym_api.dto.MembresiaDTO;
import com.main.gym_api.model.Membresia;
import com.main.gym_api.model.Miembro;
import com.main.gym_api.repository.MembresiaRepository;
import com.main.gym_api.repository.MiembroRepository;
import com.main.gym_api.service.MembresiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MembresiaServiceImpl implements MembresiaService {

    private final MembresiaRepository membresiaRepository;
    private final MiembroRepository miembroRepository;


    @Override
    public MembresiaDTO crearMembresia(MembresiaDTO dto) {
        /*Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));*/

        Membresia membresia = new Membresia();
        membresia.setTipo(dto.getTipo());
        membresia.setFechaInicio(dto.getFechaInicio());
        membresia.setFechaFin(dto.getFechaFin());
        membresia.setCosto(dto.getCosto());
        membresia.setActiva(dto.isActiva());
        //membresia.setMiembro(miembro);

        return toDTO(membresiaRepository.save(membresia));

    }

    @Override
    public List<MembresiaDTO> obtenerTodasLasMembresias() {
        return membresiaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MembresiaDTO obtenerMembresiaPorId(Long id) {
        Membresia membresia = membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        return toDTO(membresia);
    }

    @Override
    public List<MembresiaDTO> obtenerMembresiasPorMiembro(Long miembroId) {
        return membresiaRepository.findByMiembroId(miembroId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MembresiaDTO> obtenerMembresiasActivasPorMiembro(Long miembroId) {
        return membresiaRepository.findByMiembroIdAndActivaTrue(miembroId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MembresiaDTO actualizarMembresia(Long id, MembresiaDTO dto) {
        Membresia membresia = membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        membresia.setTipo(dto.getTipo());
        membresia.setFechaInicio(dto.getFechaInicio());
        membresia.setFechaFin(dto.getFechaFin());
        membresia.setActiva(dto.isActiva());
        return toDTO(membresiaRepository.save(membresia));
    }

    @Override
    public void eliminarMembresia(Long id) {
        if (!membresiaRepository.existsById(id)) {
            throw new RuntimeException("Membresía no encontrada");
        }
        membresiaRepository.deleteById(id);
    }

    @Override
    public MembresiaDTO cambiarEstadoMembresia(Long id, boolean activa) {
        Membresia membresia = membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresía no encontrada"));
        membresia.setActiva(activa);
        return toDTO(membresiaRepository.save(membresia));
    }

    // --- Mappers ---
    private MembresiaDTO toDTO(Membresia membresia) {
        MembresiaDTO dto = new MembresiaDTO();
        dto.setId(membresia.getId());
        dto.setTipo(membresia.getTipo());
        dto.setFechaInicio(membresia.getFechaInicio());
        dto.setFechaFin(membresia.getFechaFin());
        dto.setActiva(membresia.isActiva());
        dto.setCosto(membresia.getCosto());
        //dto.setMiembroId(membresia.getMiembro().getId());
        return dto;
    }
}
