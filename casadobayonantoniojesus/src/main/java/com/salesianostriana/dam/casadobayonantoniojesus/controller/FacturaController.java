package com.salesianostriana.dam.casadobayonantoniojesus.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;
import com.salesianostriana.dam.casadobayonantoniojesus.service.ClienteService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.CocheService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.FacturaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FacturaController {

    private final CocheService cocheService;
    private final ClienteService clienteService;
    private final FacturaService facturaService;

    @GetMapping("/facturas")
    public String listarFacturas(Model model) {
        List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
        model.addAttribute("facturas", facturas);
        model.addAttribute("ingresosTotales", facturaService.obtenerIngresosTotales());
        model.addAttribute("mediaIngresos", facturaService.obtenerMediaIngresos());
        model.addAttribute("topClientes", clienteService.top5ClientesConMayorGasto());
        model.addAttribute("facturasMesActual", facturaService.cantidadFacturasMesActual());
        model.addAttribute("ingresosMesActual", facturaService.ingresosMesActual());


        return "facturas";
    }

    @PostMapping("/facturas/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return "redirect:/facturas";
    }

    @GetMapping("/facturas/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
        return "formularioFactura";
    }

    @PostMapping("/facturas/guardar")
    public String guardarFactura(@ModelAttribute Factura factura) {
        facturaService.guardarFactura(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/facturas/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Factura factura = facturaService.findById(id);
        model.addAttribute("factura", factura);
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
        return "formularioFactura";
    }

    @PostMapping("/facturas/editar/{id}")
    public String actualizarFactura(@PathVariable Long id,
                                    @ModelAttribute Factura factura) {
        factura.setId(id);
        facturaService.guardarFactura(factura);
        return "redirect:/facturas";
    }
}
