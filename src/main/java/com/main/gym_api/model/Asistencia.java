package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Registra la asistencia de un miembro a una clase.
 * Permite controlar quién asistió, cuándo y si estuvo presente.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "asistencias")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private boolean presente;

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
     * Marca la asistencia como presente.
     */
    public void marcarPresente() {
        this.presente = true;
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Marca la asistencia como ausente.
     */
    public void marcarAusente() {
        this.presente = false;
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Verifica si la asistencia corresponde a una clase y miembro específicos.
     */
    public boolean correspondeA(Miembro miembro, Clase clase) {
        return this.miembro != null && this.miembro.equals(miembro)
                && this.clase != null && this.clase.equals(clase);
    }
}

