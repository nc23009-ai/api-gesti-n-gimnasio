package com.main.gym_api.model;
import jakarta.persistence.*;
import lombok.*;

@Entity // Indica que la clase es una entidad JPA que se mapeará a una tabla en la base de datos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    private int duracionSemanas;

    private int repsEstandar;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
}
