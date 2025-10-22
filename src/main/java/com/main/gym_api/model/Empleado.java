/*
 * Extiende Persona → representa personal del gimnasio (entrenadores, administrativos).
 *      Podrá impartir clases o gestionar membresías según el rol.
 * */
package com.main.gym_api.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado extends Persona{

    //atributos
    private String role, area;

    /*          Metodos a desarrollar...
     *
     *   asignarClase()
     *
     * */


}
