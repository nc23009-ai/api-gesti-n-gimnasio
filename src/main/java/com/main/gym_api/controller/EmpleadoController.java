package com.main.gym_api.controller;

import com.main.gym_api.dto.EmpleadoDTO;
import com.main.gym_api.service.EmpleadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> crear(@Valid @RequestBody EmpleadoDTO dto) {
        EmpleadoDTO creado = empleadoService.crearEmpleado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listar() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO dto) {
        return ResponseEntity.ok(empleadoService.actualizarEmpleado(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}