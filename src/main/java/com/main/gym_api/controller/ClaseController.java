package com.main.gym_api.controller;

import com.main.gym_api.dto.ClaseDTO;
import com.main.gym_api.service.ClaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
@RequiredArgsConstructor
public class ClaseController {

    private final ClaseService claseService;

    @PostMapping
    public ResponseEntity<ClaseDTO> crear(@Valid @RequestBody ClaseDTO dto) {
        ClaseDTO creado = claseService.crearClase(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<ClaseDTO>> listar() {
        return ResponseEntity.ok(claseService.listarClases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(claseService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClaseDTO dto) {
        return ResponseEntity.ok(claseService.actualizarClase(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        claseService.eliminarClase(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/inscritos")
    public ResponseEntity<Integer> inscritos(@PathVariable Long id) {
        return ResponseEntity.ok(claseService.contarInscritos(id));
    }
}
