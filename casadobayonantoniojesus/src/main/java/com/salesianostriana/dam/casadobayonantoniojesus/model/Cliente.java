package com.salesianostriana.dam.casadobayonantoniojesus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe de tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Coche> coches;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;  
}
