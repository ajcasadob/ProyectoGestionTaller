package com.salesianostriana.dam.casadobayonantoniojesus.controller;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.service.ClienteService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.CocheService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final CocheService cocheService;
    private final FacturaService facturaService;


    @GetMapping("/clientes")
    public String inicio (Model model){

        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";

    }

    @PostMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());

        model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
        model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
        return "formularioCliente";
    }

    @PostMapping("/nuevo")
    public String guardarCliente( @ModelAttribute("cliente") Cliente cliente,
                                 BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
            model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
            return "formularioCliente";
        }



        return "redirect:/clientes";
    }

}
