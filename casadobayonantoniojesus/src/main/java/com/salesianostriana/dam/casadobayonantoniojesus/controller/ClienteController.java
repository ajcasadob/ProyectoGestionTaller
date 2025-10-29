package com.salesianostriana.dam.casadobayonantoniojesus.controller;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;


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


}
