package com.main.gym_api.controller;

import com.main.gym_api.dto.InscripcionDTO;
import com.main.gym_api.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<InscripcionDTO> crear(@Valid @RequestBody InscripcionDTO dto) {
        InscripcionDTO creado = inscripcionService.crearInscripcion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> listar() {
        return ResponseEntity.ok(inscripcionService.listarInscripciones());
    }

    @GetMapping("/miembro/{miembroId}")
    public ResponseEntity<List<InscripcionDTO>> porMiembro(@PathVariable Long miembroId) {
        return ResponseEntity.ok(inscripcionService.listarPorMiembro(miembroId));
    }

    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<InscripcionDTO>> porClase(@PathVariable Long claseId) {
        return ResponseEntity.ok(inscripcionService.listarPorClase(claseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<InscripcionDTO> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(inscripcionService.actualizarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
