/*
 *  Representa una suscripción del miembro.
 *      Controla el estado y duración de las membresías.
 * */
package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "membresias")
public class Membresia {

    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaFin;
    private Double costo;
    private boolean activa = true;

    @OneToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    // Métodos de negocio
    public void activar() { this.activa = true; }
    public void desactivar() { this.activa = false; }

    public long calcularDuracion() {
        if (fechaInicio != null && fechaFin != null) {
            return java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        }
        return 0;
    }

    public void renovar() {
        if (fechaFin != null) {
            this.fechaFin = fechaFin.plusMonths(1);
        }
    }
}

