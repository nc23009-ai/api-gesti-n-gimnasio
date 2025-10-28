package com.main.gym_api.controller;

import com.main.gym_api.model.Miembro;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    private static final List<Miembro> miembros = new ArrayList<>();

    // Datos de ejemplo para pruebas
    static {
        miembros.add(new Miembro(1L, "Miguel", "Lopez", "miguel.lopez@correo.com", "6315-2563", "20/10/2024", true));
        miembros.add(new Miembro(2L, "Ana", "Garcia", "ana.garcia@correo.com", "7890-1234", "18/10/2024", false));
    }

    /**
     * Obtener todos los miembros
     */
    @GetMapping
    public List<Miembro> getAllMiembros() {
        return miembros;
    }

    /**
     * Obtener miembro por ID
     */
    @GetMapping("/{id}")
    public Miembro getMiembroById(@PathVariable Long id) {
        for (Miembro p : miembros) {
            if (Objects.equals(p.getId(), id)) return p;
        }
        return null; // En una versión real, lanzaríamos una excepción 404
    }

    /**
     * Crear nuevo miembro (POST /api/miembros)
     */
    @PostMapping
    public Miembro createMiembro(@RequestBody Miembro nuevoMiembro) {
        // Generar ID único (en producción usaríamos base de datos)
        Long nuevoId = miembros.stream()
                .mapToLong(Miembro::getId)
                .max()
                .orElse(0L) + 1;
        
        nuevoMiembro.setId(nuevoId);
        miembros.add(nuevoMiembro);
        
        return nuevoMiembro;
    }

    /**
     * Eliminar miembro (DELETE /api/miembros/{id})
     */
    @DeleteMapping("/{id}")
    public String deleteMiembro(@PathVariable Long id) {
        // Buscar miembro por ID
        Miembro miembroAEliminar = null;
        for (Miembro m : miembros) {
            if (Objects.equals(m.getId(), id)) {
                miembroAEliminar = m;
                break;
            }
        }
        
        if (miembroAEliminar != null) {
            miembros.remove(miembroAEliminar);
            return "Miembro con ID " + id + " eliminado correctamente";
        } else {
            return "Miembro con ID " + id + " no encontrado";
        }
    }

    /**
     * Activar miembro (PUT /api/miembros/{id}/activar)
     */
    @PutMapping("/{id}/activar")
    public Miembro activarMiembro(@PathVariable Long id) {
        for (Miembro m : miembros) {
            if (Objects.equals(m.getId(), id)) {
                m.setActiveState(true);
                return m;
            }
        }
        return null; // Miembro no encontrado
    }

    /**
     * Inactivar miembro (PUT /api/miembros/{id}/desactivar)
     */
    @PutMapping("/{id}/desactivar")
    public Miembro inactivarMiembro(@PathVariable Long id) {
        for (Miembro m : miembros) {
            if (Objects.equals(m.getId(), id)) {
                m.setActiveState(false);
                return m;
            }
        }
        return null; // Miembro no encontrado
    }
}