package com.main.gym_api.controller;

import com.main.gym_api.dto.MembresiaDTO;
import com.main.gym_api.service.MembresiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@RequiredArgsConstructor
public class MembresiaController {

    private final MembresiaService membresiaService;

    // POST - Crear nueva membresía
    @PostMapping
    public ResponseEntity<MembresiaDTO> crearMembresia(@Valid @RequestBody MembresiaDTO membresiaDTO) {
        MembresiaDTO nuevaMembresia = membresiaService.crearMembresia(membresiaDTO);
        return new ResponseEntity<>(nuevaMembresia, HttpStatus.CREATED);
    }

    // GET - Obtener todas las membresías
    @GetMapping
    public ResponseEntity<List<MembresiaDTO>> obtenerTodasLasMembresias() {
        List<MembresiaDTO> membresias = membresiaService.obtenerTodasLasMembresias();
        return ResponseEntity.ok(membresias);
    }

    // GET - Obtener membresía por ID
    @GetMapping("/{id}")
    public ResponseEntity<MembresiaDTO> obtenerMembresiaPorId(@PathVariable Long id) {
        MembresiaDTO membresia = membresiaService.obtenerMembresiaPorId(id);
        return ResponseEntity.ok(membresia);
    }

    // GET - Obtener membresías por miembro
    @GetMapping("/miembro/{miembroId}")
    public ResponseEntity<List<MembresiaDTO>> obtenerMembresiasPorMiembro(@PathVariable Long miembroId) {
        List<MembresiaDTO> membresias = membresiaService.obtenerMembresiasPorMiembro(miembroId);
        return ResponseEntity.ok(membresias);
    }

    // GET - Obtener membresías activas por miembro
    @GetMapping("/miembro/{miembroId}/activas")
    public ResponseEntity<List<MembresiaDTO>> obtenerMembresiasActivasPorMiembro(@PathVariable Long miembroId) {
        List<MembresiaDTO> membresias = membresiaService.obtenerMembresiasActivasPorMiembro(miembroId);
        return ResponseEntity.ok(membresias);
    }

    // PUT - Actualizar membresía existente
    @PutMapping("/{id}")
    public ResponseEntity<MembresiaDTO> actualizarMembresia(
            @PathVariable Long id, 
            @Valid @RequestBody MembresiaDTO membresiaDTO) {
        MembresiaDTO membresiaActualizada = membresiaService.actualizarMembresia(id, membresiaDTO);
        return ResponseEntity.ok(membresiaActualizada);
    }

    // DELETE - Eliminar membresía
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMembresia(@PathVariable Long id) {
        membresiaService.eliminarMembresia(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH - Cambiar estado de membresía
    @PatchMapping("/{id}/estado")
    public ResponseEntity<MembresiaDTO> cambiarEstadoMembresia(
            @PathVariable Long id, 
            @RequestParam boolean activa) {
        MembresiaDTO membresiaActualizada = membresiaService.cambiarEstadoMembresia(id, activa);
        return ResponseEntity.ok(membresiaActualizada);
    }
}