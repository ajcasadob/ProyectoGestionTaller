package com.salesianostriana.dam.casadobayonantoniojesus.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping("/")
    public String welcome (Model model){

        model.addAttribute("nombre","Esto funciona");
        return "index";
    }

}
