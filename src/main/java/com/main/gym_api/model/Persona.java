
package com.main.gym_api.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class Persona {

    // Identificador único heredado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = true)
    protected String nombre;

    @Column(nullable = true)
    protected String apellido;

    @Column(unique = true, nullable = true)
    protected String email;

    @Column(length = 15)
    protected String telefono;
}

