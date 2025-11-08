package com.main.gym_api.controller;

import com.main.gym_api.dto.MiembroDTO;
import com.main.gym_api.service.MiembroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
@RequiredArgsConstructor
public class MiembroController {

    private final MiembroService miembroService;

    // POST - Crear nuevo miembro
    @PostMapping
    public ResponseEntity<MiembroDTO> crearMiembro(@Valid @RequestBody MiembroDTO miembroDTO) {
        MiembroDTO nuevoMiembro = miembroService.crearMiembro(miembroDTO);
        return new ResponseEntity<>(nuevoMiembro, HttpStatus.CREATED);
    }

    // GET - Obtener todos los miembros
    @GetMapping
    public ResponseEntity<List<MiembroDTO>> obtenerTodosLosMiembros() {
        List<MiembroDTO> miembros = miembroService.obtenerTodosLosMiembros();
        return ResponseEntity.ok(miembros);
    }

    // GET - Obtener miembro por ID
    @GetMapping("/{id}")
    public ResponseEntity<MiembroDTO> obtenerMiembroPorId(@PathVariable Long id) {
        MiembroDTO miembro = miembroService.obtenerMiembroPorId(id);
        return ResponseEntity.ok(miembro);
    }

    // PUT - Actualizar miembro existente
    @PutMapping("/{id}")
    public ResponseEntity<MiembroDTO> actualizarMiembro(
            @PathVariable Long id, 
            @Valid @RequestBody MiembroDTO miembroDTO) {
        MiembroDTO miembroActualizado = miembroService.actualizarMiembro(id, miembroDTO);
        return ResponseEntity.ok(miembroActualizado);
    }

    // DELETE - Eliminar miembro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMiembro(@PathVariable Long id) {
        miembroService.eliminarMiembro(id);
        return ResponseEntity.noContent().build();
    }

    // GET - Obtener miembros activos
    @GetMapping("/activos")
    public ResponseEntity<List<MiembroDTO>> obtenerMiembrosActivos() {
        List<MiembroDTO> miembrosActivos = miembroService.obtenerMiembrosActivos();
        return ResponseEntity.ok(miembrosActivos);
    }

    // PATCH - Cambiar estado de miembro
    @PatchMapping("/{id}/estado")
    public ResponseEntity<MiembroDTO> cambiarEstadoMiembro(
            @PathVariable Long id, 
            @RequestParam boolean activo) {
        MiembroDTO miembroActualizado = miembroService.cambiarEstadoMiembro(id, activo);
        return ResponseEntity.ok(miembroActualizado);
    }
}