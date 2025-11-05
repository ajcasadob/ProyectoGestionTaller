package com.salesianostriana.dam.casadobayonantoniojesus.service;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IFacturaRepository;

@Service
@RequiredArgsConstructor
public class FacturaService {


    private final IFacturaRepository facturaRepository;


    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }


}
