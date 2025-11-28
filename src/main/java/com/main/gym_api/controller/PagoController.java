package com.main.gym_api.controller;

import com.main.gym_api.dto.PagoDTO;
import com.main.gym_api.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoDTO> registrar(@Valid @RequestBody PagoDTO dto) {
        PagoDTO creado = pagoService.registrarPago(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }

    @GetMapping("/miembro/{miembroId}")
    public ResponseEntity<List<PagoDTO>> porMiembro(@PathVariable Long miembroId) {
        return ResponseEntity.ok(pagoService.listarPorMiembro(miembroId));
    }

    @GetMapping("/membresia/{membresiaId}")
    public ResponseEntity<List<PagoDTO>> porMembresia(@PathVariable Long membresiaId) {
        return ResponseEntity.ok(pagoService.listarPorMembresia(membresiaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

