package com.salesianostriana.dam.casadobayonantoniojesus.service;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IClienteRepository;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.ICocheRepository;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IFacturaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CocheService {

    private final ICocheRepository cocheRepository;
    private final IClienteRepository clienteRepository;
    private final IFacturaRepository facturaRepository;

    public List<Coche> obtenerTodosLosCoches() {
        return cocheRepository.findAll();
    }



    public void eliminarCoche(Long id) {
    Coche coche = cocheRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Coche no encontrado con ID: " ));

    
    List<Factura> facturas = facturaRepository.findAllByCoche(coche);

    for (Factura factura : facturas) {
        factura.setCoche(null);
        facturaRepository.save(factura);
    }

   
    cocheRepository.delete(coche);
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

    public List<Coche> top5CochesConMasReparaciones() {
        return cocheRepository.findAll().stream()
                .sorted((c1, c2) -> Long.compare(
                        facturaRepository.countByCocheId(c2.getId()),
                        facturaRepository.countByCocheId(c1.getId())))
                .limit(5)
                .collect(Collectors.toList());
    }


    public Map<String, Long> contarCochesPorMarca() {
        return cocheRepository.findAll().stream()
                .filter(c -> c.getMarca() != null && !c.getMarca().isBlank())
                .collect(Collectors.groupingBy(Coche::getMarca, Collectors.counting()));
    }
    public long contarFacturasPorCoche(Long cocheId) {
        return facturaRepository.countByCocheId(cocheId);
    }





}
