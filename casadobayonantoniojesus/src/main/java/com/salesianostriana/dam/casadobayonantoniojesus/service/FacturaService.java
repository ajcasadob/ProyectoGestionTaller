package com.salesianostriana.dam.casadobayonantoniojesus.service;


import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Factura> facturasDelUltimoMes() {
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        return facturaRepository.findAll().stream()
                .filter(f -> f.getFecha() != null && f.getFecha().isAfter(haceUnMes))
                .sorted(Comparator.comparing(Factura::getFecha).reversed())
                .collect(Collectors.toList());
    }

    public double ingresosMesActual() {
        return facturasDelUltimoMes().stream()
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public long cantidadFacturasMesActual() {
        return facturasDelUltimoMes().size();
    }



}
