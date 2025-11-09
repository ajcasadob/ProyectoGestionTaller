package com.salesianostriana.dam.casadobayonantoniojesus.service;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IClienteRepository;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.ICocheRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocheService {

    private final ICocheRepository cocheRepository;
    private final IClienteRepository clienteRepository;

    public List<Coche> obtenerTodosLosCoches() {
        return cocheRepository.findAll();
    }

    public void eliminarCoche(Long id) {
        cocheRepository.deleteById(id);
    }

    public List<Coche> buscarPorMatricula(String matricula) {
        String filtro = (matricula != null) ? matricula.trim() : "";
        return filtro.isEmpty()
                ? obtenerTodosLosCoches()
                : cocheRepository.findByMatriculaContainingIgnoreCase(filtro);
    }

    public void guardarCoche(Coche coche) {
        cocheRepository.save(coche);
    }

    public void guardarOActualizarCoche(Coche coche, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));
        coche.setCliente(cliente);
        cocheRepository.save(coche);
    }


    public Coche buscarPorId(Long id) {
        return cocheRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coche no encontrado con ID: " + id));
    }

}
