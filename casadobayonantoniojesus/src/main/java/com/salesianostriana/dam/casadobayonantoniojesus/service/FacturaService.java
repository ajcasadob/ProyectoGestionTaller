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

    public double calcularIngresosTotales (List<Factura> facturas ){

        return facturas.stream().mapToDouble(Factura::getPrecio).sum();
    }

    public double calcularMediaIngresos ( List<Factura>facturas){
        if(facturas.isEmpty()){
            return 0.0;
        }
        return calcularIngresosTotales(facturas)/facturas.size();
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

}
