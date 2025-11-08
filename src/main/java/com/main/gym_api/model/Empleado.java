/*
 * Extiende Persona → representa personal del gimnasio (entrenadores, administrativos).
 *      Podrá impartir clases o gestionar membresías según el rol.
 * */
package com.main.gym_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "empleados")
public class Empleado extends Persona{

    //atributos
    @NotBlank(message = "El rol no puede estar vacío")
    private String role;

    @NotBlank(message = "El área no puede estar vacía")
    private String area;

    // Relación con Clase
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<Clase> clases = new ArrayList<>();

    /**
     * Asigna una clase al empleado (entrenador o instructor).
     * - Establece la relación bidireccional
     * - Evita duplicados
     */
    public void asignarClase(Clase clase) {
        if (clase == null) return;
        if (!clases.contains(clase)) {
            clases.add(clase);
            clase.setEmpleado(this);
        }
    }

    /**
     * Elimina una clase asignada al empleado.
     */
    public void removerClase(Clase clase) {
        if (clase == null) return;
        if (clases.contains(clase)) {
            clases.remove(clase);
            clase.setEmpleado(null);
        }
    }


}
