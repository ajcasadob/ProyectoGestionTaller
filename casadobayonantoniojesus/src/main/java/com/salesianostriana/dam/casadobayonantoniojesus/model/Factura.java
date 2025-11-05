package com.salesianostriana.dam.casadobayonantoniojesus.model;


import jakarta.persistence.*;
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

    private String descripcion;
    private double precio;
    private double ingresosTotales;
    private double mediaIngresos;
    private String pieza;
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name= "cliente_id")  
    private Cliente cliente;
    
    
    @ManyToOne
    @JoinColumn(name= "coche_id")
    private Coche coche;
}
