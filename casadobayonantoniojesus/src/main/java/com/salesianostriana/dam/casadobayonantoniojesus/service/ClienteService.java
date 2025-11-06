package com.salesianostriana.dam.casadobayonantoniojesus.service;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Cliente;
import com.salesianostriana.dam.casadobayonantoniojesus.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final IClienteRepository clienteRepository;


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
}
