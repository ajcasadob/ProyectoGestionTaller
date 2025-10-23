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
public class Coche {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String matricula;
    private String marca;
    private String modelo;
    private double kms;
    private String imagenUrl;
    private String problema;


    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    @ManyToOne
    @JoinColumn(name = "cliente_id" )


    private Cliente cliente;
}
