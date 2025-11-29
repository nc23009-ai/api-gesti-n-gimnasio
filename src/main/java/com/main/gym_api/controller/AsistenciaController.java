package com.main.gym_api.controller;

import com.main.gym_api.dto.AsistenciaDTO;
import com.main.gym_api.service.AsistenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @PostMapping
    public ResponseEntity<AsistenciaDTO> registrar(@Valid @RequestBody AsistenciaDTO dto) {
        AsistenciaDTO creado = asistenciaService.registrarAsistencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> listar() {
        return ResponseEntity.ok(asistenciaService.listarTodas());
    }

    @GetMapping("/miembro/{miembroId}")
    public ResponseEntity<List<AsistenciaDTO>> porMiembro(@PathVariable Long miembroId) {
        return ResponseEntity.ok(asistenciaService.listarPorMiembro(miembroId));
    }

    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<AsistenciaDTO>> porClase(@PathVariable Long claseId) {
        return ResponseEntity.ok(asistenciaService.listarPorClase(claseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(asistenciaService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

