package com.salesianostriana.dam.casadobayonantoniojesus.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Factura {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción es un campo obligatorio")
    private String descripcion;

    @PositiveOrZero(message = "El precio no puede ser negativo")
    private double precio;

    private double ingresosTotales;
    private double mediaIngresos;

    @NotBlank(message = "La pieza cambiada es obligatoria")
    private String pieza;


    @NotBlank(message = "La fecha debe de ser obligatoria")
    @PastOrPresent(message = "La fecha debe ser igual o anterior al dia actual")
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name= "cliente_id")  
    private Cliente cliente;
    
    
    @ManyToOne
    @JoinColumn(name= "coche_id")
    private Coche coche;
}
