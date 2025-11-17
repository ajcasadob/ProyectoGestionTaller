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

    public double calcularIngresosTotales(List<Factura> facturas) {
        return facturas.stream().mapToDouble(Factura::getPrecio).sum();
    }

    public double calcularMediaIngresos(List<Factura> facturas) {
        return facturas.isEmpty() ? 0.0 : calcularIngresosTotales(facturas) / facturas.size();
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

    public Factura aplicarDescuentoFactura(Factura factura, double descuentoPorcentaje) {
        factura.setPrecio(factura.getPrecio() * (1 - descuentoPorcentaje / 100.0));
        return factura;
    }

    // Métodos para estadística global
    public double obtenerIngresosTotales() {
        return obtenerTodasLasFacturas().stream()
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public double obtenerMediaIngresos() {
        List<Factura> facturas = obtenerTodasLasFacturas();
        double total = obtenerIngresosTotales();
        return facturas.isEmpty() ? 0 : total / facturas.size();
    }


}
