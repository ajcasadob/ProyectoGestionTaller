package com.salesianostriana.dam.casadobayonantoniojesus.controller;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import com.salesianostriana.dam.casadobayonantoniojesus.service.CocheService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CocheController {


    private final CocheService cocheService;

    @GetMapping("/coches")
    public String listarCoches(Model model) {
        List<Coche> coches = cocheService.obtenerTodosLosCoches();
        model.addAttribute("coches", coches);



        return "coches";
    }
}
