package com.main.gym_api.service.impl;

import com.main.gym_api.dto.PagoDTO;
import com.main.gym_api.model.*;
import com.main.gym_api.repository.*;
import com.main.gym_api.service.PagoService;
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
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final MiembroRepository miembroRepository;
    private final MembresiaRepository membresiaRepository;

    @Override
    public PagoDTO registrarPago(PagoDTO dto) {
        Miembro miembro = miembroRepository.findById(dto.getMiembroId())
                .orElseThrow(() -> new EntityNotFoundException("Miembro no encontrado"));

        Pago p = new Pago();
        p.setMonto(dto.getMonto());
        p.setFechaPago(dto.getFechaPago());
        p.setMetodo(dto.getMetodo());
        p.setMiembro(miembro);

        if (dto.getMembresiaId() != null) {
            Membresia m = membresiaRepository.findById(dto.getMembresiaId())
                    .orElseThrow(() -> new EntityNotFoundException("Membresía no encontrada"));
            p.setMembresia(m);
        }

        Pago saved = pagoRepository.save(p);
        return EntityMapper.toPagoDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoDTO> listarTodos() {
        return pagoRepository.findAll().stream().map(EntityMapper::toPagoDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> listarPorMiembro(Long miembroId) {
        return pagoRepository.findByMiembroId(miembroId).stream().map(EntityMapper::toPagoDTO).collect(Collectors.toList());
    }

    @Override
    public List<PagoDTO> listarPorMembresia(Long membresiaId) {
        return pagoRepository.findByMembresiaId(membresiaId).stream().map(EntityMapper::toPagoDTO).collect(Collectors.toList());
    }

    @Override
    public PagoDTO obtenerPorId(Long id) {
        Pago p = pagoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pago no encontrado"));
        return EntityMapper.toPagoDTO(p);
    }

    @Override
    public void eliminar(Long id) {
        if (!pagoRepository.existsById(id)) throw new EntityNotFoundException("Pago no encontrado");
        pagoRepository.deleteById(id);
    }
}

