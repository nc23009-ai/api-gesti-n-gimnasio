package com.main.gym_api.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monto;

    private LocalDate fechaPago;

    private String metodo;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;
}
