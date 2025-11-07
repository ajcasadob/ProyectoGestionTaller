package com.salesianostriana.dam.casadobayonantoniojesus.service;


import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Taller;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.ITallerRepository;

@Service
@RequiredArgsConstructor
public class TallerService {


    private final ITallerRepository tallerRepository;



    public Taller obtenerTaller() {
        return tallerRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró ningún taller en la base de datos"));
    }

    /**
     * Guarda o actualiza el taller.
     * Como solo hay un taller, simplemente lo guarda.
     */
    public Taller guardar(Taller taller) {
        return tallerRepository.save(taller);
    }

    /**
     * Verifica si existe el taller en la base de datos
     */
    public boolean existeTaller() {
        return tallerRepository.count() > 0;
    }

    /**
     * Método legacy (mantener por compatibilidad)
     */
    public List<Taller> obtenerTodo() {
        return tallerRepository.findAll();
    }
}
