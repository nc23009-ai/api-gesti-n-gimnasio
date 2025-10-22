/*
 *  Representa una suscripción del miembro.
 *      Controla el estado y duración de las membresías.
 * */
package com.main.gym_api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membresia {

    //atributos
    private Long id;
    private String type, startDate, endDate;
    private Double price;
    private Boolean activeState;



    /*          Metodos a desarrollar...
    *
    *   activar() y desactivar() --> cambio de estado
    *   calcularDuracion()
    *   renovar()
    *   asignarMiembro()
    *
    * */



}
