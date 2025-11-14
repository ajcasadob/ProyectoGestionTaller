package com.salesianostriana.dam.casadobayonantoniojesus.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taller {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    @NotBlank(message = "El nif de la empresa es obligatorio")
    private String nif;
    @NotBlank(message = "El horario de apertura es obligatorio")
    private String horario;
    @NotBlank(message = "La fecha de creación es obligatoria")
    private LocalDate fechaCreacion;
}
