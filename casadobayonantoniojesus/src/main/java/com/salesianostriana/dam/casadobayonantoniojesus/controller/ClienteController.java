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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final CocheService cocheService;
    private final FacturaService facturaService;

    @GetMapping("/clientes")
    public String inicio(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @PostMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
        model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
        return "formularioCliente";
    }

    @PostMapping("/clientes/nuevo")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
            model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
            return "formularioCliente";
        }

        clienteService.guardarCliente(cliente);

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Long id, Model model) {

        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findbyId(id));
        model.addAttribute("cliente", cliente.get());
        model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
        model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
        return "formularioCliente";
    }

    @PostMapping("/clientes/editar/{id}")
    public String actualizarCliente(@PathVariable Long id,
            @ModelAttribute("cliente") Cliente cliente,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("coches", cocheService.obtenerTodosLosCoches());
            model.addAttribute("facturas", facturaService.obtenerTodasLasFacturas());
            return "formularioCliente";
        }

        clienteService.actulizarCliente(cliente, id);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/total-facturado")
    public String mostrarTotalFacturado(@PathVariable Long id, Model model) {
        double totalFacturado = clienteService.totalFacturadoPorCliente(id);
        model.addAttribute("totalFacturado", totalFacturado);
        model.addAttribute("cliente", clienteService.findbyId(id));
        return "ClienteTotalFacturado";
    }



}