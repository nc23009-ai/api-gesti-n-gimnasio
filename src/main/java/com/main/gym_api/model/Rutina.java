package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa una rutina de entrenamiento asignada a un miembro del gimnasio.
 * Puede incluir ejercicios, duración y equipo utilizado.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String descripcion;

    @Column(nullable = false)
    private int duracionSemanas;

    @Column(nullable = false)
    private int repsEstandar;

    // Fecha de inicio opcional (útil para reportes)
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    // Relación con Miembro (una rutina pertenece a un miembro)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id", nullable = false)
    @ToString.Exclude
    private Miembro miembro;

    // Relación con Equipo (una rutina puede usar un equipo)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id", nullable = false)
    @ToString.Exclude
    private Equipo equipo;

    /**
     * Calcula la fecha estimada de finalización de la rutina.
     */
    public LocalDate calcularFechaFin() {
        if (fechaInicio == null) return null;
        return fechaInicio.plusWeeks(duracionSemanas);
    }

    /**
     * Asigna un nuevo equipo a la rutina.
     */
    public void asignarEquipo(Equipo nuevoEquipo) {
        if (nuevoEquipo != null) {
            this.equipo = nuevoEquipo;
        }
    }

    /**
     * Verifica si la rutina está en curso en la fecha actual.
     */
    public boolean estaEnCurso() {
        if (fechaInicio == null) return false;
        LocalDate hoy = LocalDate.now();
        LocalDate fin = calcularFechaFin();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fin);
    }
}

