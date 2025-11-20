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

    private static final double IVA_PORCENTAJE = 21.0;



    public double calcularPrecioConIva(Factura factura) {
        double iva = factura.getPrecio() * (IVA_PORCENTAJE / 100);
        return factura.getPrecio() + iva;
    }

    public double obtenerPrecioSinIva(Factura factura) {
        return factura.getPrecio();
    }

    public double calcularImporteIva(Factura factura) {
        return factura.getPrecio() * (IVA_PORCENTAJE / 100);
    }



    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }


    public double calcularIngresosTotales(List<Factura> facturas) {
        return facturas.stream().mapToDouble(Factura::getPrecio).sum();
    }

    public double calcularMediaIngresos(List<Factura> facturas) {
        return facturas.isEmpty() ? 0.0 : calcularIngresosTotales(facturas) / facturas.size();
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



    public double calcularIngresosTotalesConIva() {
        return obtenerTodasLasFacturas().stream()
                .mapToDouble(this::calcularPrecioConIva)
                .sum();
    }

    public double calcularTotalIvaRecaudado() {
        return obtenerTodasLasFacturas().stream()
                .mapToDouble(this::calcularImporteIva)
                .sum();
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

    public double ingresosMesActualConIva() {
        return facturasDelUltimoMes().stream()
                .mapToDouble(this::calcularPrecioConIva)
                .sum();
    }

    public long cantidadFacturasMesActual() {
        return facturasDelUltimoMes().size();
    }


}
