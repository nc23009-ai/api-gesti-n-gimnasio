package com.main.gym_api.controller;

import com.main.gym_api.model.Miembro;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {

    private static final List<Miembro> miembros = new ArrayList<>();

    static {
        miembros.add(new Miembro(1L, "Miguel", "Lopez", "miguel.lopez@correo.com", "6315-2563", "20/10/2024", true));

    }

    @GetMapping
    public List<Miembro> getAllMiembros(){
        return miembros;
    }

    @GetMapping("/{id}")
    public Miembro getMemberById(@PathVariable Long id){
        for(Miembro p : miembros){
            if(Objects.equals(p.getId(), id)) return p;
        }
        return null;
    }

    // crear nuevo miembro (endpoint: /api/miembros)

    // eliminar miembro (endpoint: /api/miembros/{id})

    // activar miembro (endpoint: /api/miembros/{id}/activar)

    // inactivar miembro (endpoint: /api/miembros/{id}/desactivar)

}
