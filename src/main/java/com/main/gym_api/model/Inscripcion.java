package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa la inscripción de un miembro a una clase del gimnasio.
 * Cada inscripción tiene fecha, estado y está vinculada a un miembro y a una clase.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaInscripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoInscripcion estadoInscripcion;

    // Relación con Miembro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id", nullable = false)
    @ToString.Exclude
    private Miembro miembro;

    // Relación con Clase
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id", nullable = false)
    @ToString.Exclude
    private Clase clase;

    /**
     * Cambia el estado de la inscripción.
     * Permite controlar el flujo (INSCRITO → CANCELADO → COMPLETADO)
     */
    public void cambiarEstado(EstadoInscripcion nuevoEstado) {
        if (nuevoEstado != null) {
            this.estadoInscripcion = nuevoEstado;
        }
    }

    /**
     * Marca la inscripción como completada.
     */
    public void completar() {
        this.estadoInscripcion = EstadoInscripcion.COMPLETADO;
    }

    /**
     * Marca la inscripción como cancelada.
     */
    public void cancelar() {
        this.estadoInscripcion = EstadoInscripcion.CANCELADO;
    }
}

