package com.main.gym_api.controller;

import com.main.gym_api.model.Membresia;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    private static final List<Membresia> membresias = new ArrayList<>();

    // Datos de ejemplo para pruebas
    static {
        membresias.add(new Membresia(1L, "standard", "20-10-2025", "20-11-2025", 20.00, true));
        membresias.add(new Membresia(2L, "premium", "15-10-2025", "15-12-2025", 35.00, false));
    }

    /**
     * Obtener todas las membresías
     */
    @GetMapping
    public List<Membresia> getAllMembresias() {
        return membresias;
    }

    /**
     * Obtener membresía por ID
     */
    @GetMapping("/{id}")
    public Membresia getMembresiaById(@PathVariable Long id) {
        for (Membresia p : membresias) {
            if (Objects.equals(p.getId(), id)) return p;
        }
        return null; // En una versión completa, lanzaríamos una excepción 404
    }

    /**
     * Crear nueva membresía (POST /api/membresias)
     */
    @PostMapping
    public Membresia createMembresia(@RequestBody Membresia nuevaMembresia) {
        // Generar ID único (en producción usaríamos base de datos)
        Long nuevoId = membresias.stream()
                .mapToLong(Membresia::getId)
                .max()
                .orElse(0L) + 1;
        
        nuevaMembresia.setId(nuevoId);
        membresias.add(nuevaMembresia);
        
        return nuevaMembresia;
    }

    /**
     * Eliminar membresía (DELETE /api/membresias/{id})
     */
    @DeleteMapping("/{id}")
    public String deleteMembresia(@PathVariable Long id) {
        // Buscar membresía por ID
        Membresia membresiaAEliminar = null;
        for (Membresia m : membresias) {
            if (Objects.equals(m.getId(), id)) {
                membresiaAEliminar = m;
                break;
            }
        }
        
        if (membresiaAEliminar != null) {
            membresias.remove(membresiaAEliminar);
            return "Membresía con ID " + id + " eliminada correctamente";
        } else {
            return "Membresía con ID " + id + " no encontrada";
        }
    }

    /**
     * Activar membresía (PUT /api/membresias/{id}/activar)
     */
    @PutMapping("/{id}/activar")
    public Membresia activarMembresia(@PathVariable Long id) {
        for (Membresia m : membresias) {
            if (Objects.equals(m.getId(), id)) {
                m.setActiveState(true);
                return m;
            }
        }
        return null; // Membresía no encontrada
    }

    /**
     * Inactivar membresía (PUT /api/membresias/{id}/desactivar)
     */
    @PutMapping("/{id}/desactivar")
    public Membresia inactivarMembresia(@PathVariable Long id) {
        for (Membresia m : membresias) {
            if (Objects.equals(m.getId(), id)) {
                m.setActiveState(false);
                return m;
            }
        }
        return null; // Membresía no encontrada
    }

    /**
     * Asignar membresía a miembro (POST /api/membresias/asignar/{miembroId})
     * NOTA: Esta funcionalidad requiere integración con el servicio de miembros
     */
    @PostMapping("/asignar/{miembroId}")
    public String asignarMembresiaAMiembro(@PathVariable Long miembroId, @RequestBody Membresia membresia) {
        // En una implementación real, aquí:
        // 1. Buscaríamos el miembro por ID
        // 2. Asignaríamos la membresía al miembro
        // 3. Actualizaríamos en base de datos
        
        return "Membresía asignada al miembro con ID: " + miembroId + 
               " | Tipo: " + membresia.getType() + 
               " | Precio: $" + membresia.getPrice();
    }
}