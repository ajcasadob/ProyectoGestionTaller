package com.salesianostriana.dam.casadobayonantoniojesus.service;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final IClienteRepository clienteRepository;


    public List<Cliente> buscarPorNombre(String nombre){
        String filtro = (nombre != null) ? nombre.trim() : "";
        return filtro.isEmpty()
                ? obtenerTodosLosClientes()
                : clienteRepository.findByNombreContainingIgnoreCase(filtro);
    }
    public List<Cliente> obtenerTodosLosClientes(){
        return clienteRepository.findAll();
    }

    public void eliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public Cliente findbyId(Long id){
        return clienteRepository.findById(id).orElse(null);

    }

    public void guardarCliente (Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void actulizarCliente (Cliente cliente, Long id){

        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            cliente.setId(id);
            clienteRepository.save(cliente);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    public double totalFacturadoPorCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> cliente.getFacturas().stream()
                        .mapToDouble(Factura::getPrecio)
                        .sum())
                .orElse(0.0);
    }

    public List<Cliente> top5ClientesConMayorGasto() {
        return clienteRepository.findAll().stream()
                .sorted((c1, c2) -> Double.compare(
                        totalFacturadoPorCliente(c2.getId()),
                        totalFacturadoPorCliente(c1.getId())))
                .limit(5)
                .collect(Collectors.toList());
    }

    public Map<Long, Double> obtenerGastosTop5Clientes() {
        return top5ClientesConMayorGasto().stream()
                .collect(Collectors.toMap(
                        Cliente::getId,
                        cliente -> totalFacturadoPorCliente(cliente.getId())
                ));
    }


}
