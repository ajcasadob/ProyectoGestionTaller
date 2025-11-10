package com.salesianostriana.dam.casadobayonantoniojesus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Taller;
import com.salesianostriana.dam.casadobayonantoniojesus.service.ClienteService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.CocheService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.FacturaService;
import com.salesianostriana.dam.casadobayonantoniojesus.service.TallerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TallerController {

    private final TallerService tallerService;
    private final ClienteService clienteService;
    private final CocheService cocheService;
    private final FacturaService facturaService;

    @GetMapping("/taller")
    public String verTaller(Model model) {

        Taller taller = tallerService.obtenerTaller();

        model.addAttribute("taller", taller);
        model.addAttribute("totalClientes", clienteService.obtenerTodosLosClientes().size());
        model.addAttribute("totalCoches", cocheService.obtenerTodosLosCoches().size());
        model.addAttribute("totalFacturas", facturaService.obtenerTodasLasFacturas().size());

        return "taller";
    }

    @GetMapping("/taller/editar")
    public String mostrarFormularioEditar(Model model) {
        Taller taller = tallerService.obtenerTaller();
        model.addAttribute("taller", taller);
        return "formularioTaller";
    }

    @PostMapping("/taller/actualizar")
    public String actualizarTaller(@ModelAttribute Taller taller) {
        tallerService.guardar(taller);
        return "redirect:/taller";
    }
}
