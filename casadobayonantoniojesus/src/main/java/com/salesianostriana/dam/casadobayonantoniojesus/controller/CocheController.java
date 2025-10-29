package com.salesianostriana.dam.casadobayonantoniojesus.controller;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import com.salesianostriana.dam.casadobayonantoniojesus.service.CocheService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/coches/eliminar/{id}")
    public String eliminarCoche(@PathVariable Long id) {
        cocheService.eliminarCoche(id);
        return "redirect:/coches";
    }


    @GetMapping("/coches/buscar")
    public String listarCoches(
            @RequestParam(value = "matricula", required = false) String matricula,
            Model model) {

        List<Coche> coches = cocheService.buscarPorMatricula(matricula);
        model.addAttribute("coches", coches);
        model.addAttribute("matricula", matricula); // Para mantener el valor en el input de búsqueda
        return "coches";
    }
}
