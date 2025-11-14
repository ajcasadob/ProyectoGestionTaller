package com.salesianostriana.dam.casadobayonantoniojesus.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coche {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La matricula es obligatoria")
    private String matricula;
    @NotBlank(message = "La marca es obligatoria")
    private String marca;
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @PositiveOrZero(message = "Los kms deben de ser positivo")
    private double kms;
    @NotBlank(message = "El problema es obligatorio")
    private String problema;

    

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
