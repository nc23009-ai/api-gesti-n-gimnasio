package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    private int cupoMaximo;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado instructor;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;

}
