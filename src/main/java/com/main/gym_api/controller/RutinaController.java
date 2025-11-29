package com.main.gym_api.controller;

import com.main.gym_api.dto.RutinaDTO;
import com.main.gym_api.service.RutinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@RequiredArgsConstructor
public class RutinaController {

    private final RutinaService rutinaService;

    @PostMapping
    public ResponseEntity<RutinaDTO> crear(@Valid @RequestBody RutinaDTO dto) {
        RutinaDTO creado = rutinaService.crearRutina(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<RutinaDTO>> listar() {
        return ResponseEntity.ok(rutinaService.listarRutinas());
    }

    @GetMapping("/miembro/{miembroId}")
    public ResponseEntity<List<RutinaDTO>> porMiembro(@PathVariable Long miembroId) {
        return ResponseEntity.ok(rutinaService.listarPorMiembro(miembroId));
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<RutinaDTO>> porEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(rutinaService.listarPorEquipo(equipoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(rutinaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RutinaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RutinaDTO dto) {
        return ResponseEntity.ok(rutinaService.actualizarRutina(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rutinaService.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }
}

