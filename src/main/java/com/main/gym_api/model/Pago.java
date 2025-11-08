package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Representa un pago realizado por un miembro del gimnasio.
 * Puede estar asociado a una membresía o clase específica.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double monto;

    @Column(nullable = false)
    private LocalDate fechaPago;

    @Column(nullable = false, length = 30)
    private String metodo;

    // Relación con Miembro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id", nullable = false)
    @ToString.Exclude
    private Miembro miembro;

    // Relación opcional con Membresía
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membresia_id")
    @ToString.Exclude
    private Membresia membresia;

    /**
     * Verifica si el pago fue realizado en una fecha válida (no futura).
     */
    public boolean esPagoValido() {
        return fechaPago != null && !fechaPago.isAfter(LocalDate.now()) && monto > 0;
    }

    /**
     * Calcula si el pago cubre el costo de la membresía asociada.
     */
    public boolean cubreMembresia() {
        if (membresia == null) return false;
        return monto >= membresia.getCosto();
    }

    /**
     * Marca el pago como completado (actualiza la fecha actual si no está definida).
     */
    public void registrarPago() {
        if (fechaPago == null) {
            fechaPago = LocalDate.now();
        }
    }
}
