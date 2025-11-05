package com.salesianostriana.dam.casadobayonantoniojesus.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String dni;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Coche> coches;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;  
}
