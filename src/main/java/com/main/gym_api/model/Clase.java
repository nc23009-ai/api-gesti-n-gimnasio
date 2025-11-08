package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private int cupoMaximo;

    // Relación con Empleado (instructor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    @ToString.Exclude
    private Empleado empleado;

    // Relación con Inscripciones
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Inscripcion> inscripciones = new ArrayList<>();

    // Relación con Asistencias
    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Asistencia> asistencias = new ArrayList<>();

    /**
     * Método auxiliar: agrega una inscripción a la clase.
     */
    public void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null && !inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
            inscripcion.setClase(this);
        }
    }

    /**
     * Método auxiliar: registra asistencia en la clase.
     */
    public void registrarAsistencia(Asistencia asistencia) {
        if (asistencia != null && !asistencias.contains(asistencia)) {
            asistencias.add(asistencia);
            asistencia.setClase(this);
        }
    }

}
