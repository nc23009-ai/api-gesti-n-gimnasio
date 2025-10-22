package com.main.gym_api.controller;

import com.main.gym_api.model.Membresia;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {

    private static final List<Membresia> membresias = new ArrayList<>();

    static {
        membresias.add(new Membresia(1L, "standard", "20-10-2025", "20-11-2025",20.00, true ));

    }

    @GetMapping
    public List<Membresia> getAllMembresias(){
        return membresias;
    }

    @GetMapping("/{id}")
    public Membresia getMemberById(@PathVariable Long id){
        for(Membresia p : membresias){
            if(Objects.equals(p.getId(), id)) return p;
        }
        return null;
    }

    // crear nuevo Membresia (endpoint: /api/membresias)

    // eliminar Membresia (endpoint: /api/membresias/{id})

    // activar Membresia (endpoint: /api/membresias/{id}/activar)

    // inactivar Membresia (endpoint: /api/membresias/{id}/desactivar)

    // Asignar membresia a miembro (endpoint: /api/membresias/asignar/{miembroId})
}
