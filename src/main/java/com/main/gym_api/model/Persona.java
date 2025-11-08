/*
* Clase base (abstracta) para reutilizar atributos comunes entre Empleado y Miembro
* Uso: Define campos comunes. No se instancia directamente. Empleado y Miembro heredan sus atributos.
* */
package com.main.gym_api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    //Atributos
    private Long id;
    private String name, lastname, email, phone;


}
