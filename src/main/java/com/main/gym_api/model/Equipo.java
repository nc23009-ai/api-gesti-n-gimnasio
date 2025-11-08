package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo o máquina del gimnasio.
 * Cada equipo puede ser utilizado por una o varias rutinas.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEquipo estadoEquipo;

    // Relación con Rutina (1 equipo puede estar en varias rutinas)
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Rutina> rutinas = new ArrayList<>();

    /**
     * Cambia el estado del equipo.
     */
    public void cambiarEstado(EstadoEquipo nuevoEstado) {
        if (nuevoEstado != null) {
            this.estadoEquipo = nuevoEstado;
        }
    }

    /**
     * Marca el equipo como fuera de servicio.
     */
    public void marcarFueraDeUso() {
        this.estadoEquipo = EstadoEquipo.FUERA_DE_USO;
    }

    /**
     * Agrega una rutina asociada al equipo.
     */
    public void agregarRutina(Rutina rutina) {
        if (rutina != null && !rutinas.contains(rutina)) {
            rutinas.add(rutina);
            rutina.setEquipo(this);
        }
    }
}