package com.salesianostriana.dam.casadobayonantoniojesus.controller;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.service.ClienteService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ClienteService clienteService;

@GetMapping("/")
    public String inicio(Model model) {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

}
