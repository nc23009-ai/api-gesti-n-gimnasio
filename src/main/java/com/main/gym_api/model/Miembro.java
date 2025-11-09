/*
*  Representa a un cliente activo o inactivo del gimnasio, relacionado 1:1 con su membresía.
*
* */
package com.main.gym_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "miembros")
public class Miembro extends Persona{

    //atributos
    private LocalDate fechaRegistro;
    private boolean miembroActivo = true;

    @OneToOne(mappedBy = "miembro", cascade = CascadeType.ALL)
    private Membresia membresia;

    public void activar() {
        this.miembroActivo = true;
    }

    public void desactivar() {
        this.miembroActivo = false;
    }

    public void registrarFecha() {
        this.fechaRegistro = LocalDate.now();
    }
}

