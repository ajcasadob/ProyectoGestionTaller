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
    private final FacturaService facturaService;


    public List<Cliente> buscarPorNombre(String nombre) {
        return Optional.ofNullable(nombre)
                .map(String::trim)
                .filter(n -> !n.isEmpty())
                .map(clienteRepository::findByNombreContainingIgnoreCase)
                .orElseGet(this::obtenerTodosLosClientes);
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

    public void guardarCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }

    public void actulizarCliente(Cliente cliente, Long id){
        Optional<Cliente> c = clienteRepository.findById(id);
        if(c.isPresent()){
            cliente.setId(id);
            clienteRepository.save(cliente);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    // === Métodos SIN IVA ===

    public double totalFacturadoPorCliente(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> cliente.getFacturas().stream()
                        .mapToDouble(Factura::getPrecio)
                        .sum())
                .orElse(0.0);
    }

    // === Métodos CON IVA ===

    /**
     * Calcula el total facturado por un cliente CON IVA incluido
     */
    public double totalFacturadoPorClienteConIva(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> cliente.getFacturas().stream()
                        .mapToDouble(facturaService::calcularPrecioConIva)
                        .sum())
                .orElse(0.0);
    }

    /**
     * Obtiene los top 5 clientes con mayor gasto CON IVA
     */
    public List<Cliente> top5ClientesConMayorGastoConIva() {
        return clienteRepository.findAll().stream()
                .sorted((c1, c2) -> Double.compare(
                        totalFacturadoPorClienteConIva(c2.getId()),
                        totalFacturadoPorClienteConIva(c1.getId())))
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un mapa con los gastos de los top 5 clientes CON IVA
     */
    public Map<Long, Double> obtenerGastosTop5ClientesConIva() {
        return top5ClientesConMayorGastoConIva().stream()
                .collect(Collectors.toMap(
                        Cliente::getId,
                        cliente -> totalFacturadoPorClienteConIva(cliente.getId())
                ));
    }

    // === Métodos originales (sin IVA) mantenidos para compatibilidad ===

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
