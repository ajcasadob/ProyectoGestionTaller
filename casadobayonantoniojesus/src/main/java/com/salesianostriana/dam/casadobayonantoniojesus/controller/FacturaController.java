package com.salesianostriana.dam.casadobayonantoniojesus.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
public String listarFacturas (Model model){

    List<Factura> facturas = facturaService.obtenerTodasLasFacturas();
    model.addAttribute("facturas", facturas);
    return "facturas";


}




}
