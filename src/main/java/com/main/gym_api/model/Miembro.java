/*
*  Representa a un cliente activo o inactivo del gimnasio, relacionado 1:1 con su membresía.
*
* */
package com.main.gym_api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Miembro extends Persona{

    //atributos
    private String recordDate;
    private Boolean activeState;

    public Miembro(Long id, String name, String lastname, String email, String phone, String recordDate, Boolean activeState) {
        super(id, name, lastname, email, phone);
        this.recordDate = recordDate;
        this.activeState = activeState;
    }


    /*          Metodos a desarrollar...
     *
     *   activar() y desactivar() --> cambio de estado
     *   registrarFecha()
     *
     *
     * */

}
