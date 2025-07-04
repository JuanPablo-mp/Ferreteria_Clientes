package com.ferreteria.ferreteria.controller;

import com.ferreteria.ferreteria.model.Clientes;
import com.ferreteria.ferreteria.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// la clase ControllerClientes es un controlador REST que maneja las peticiones relacionadas con los clientes
@RestController
@RequestMapping("/api/clientes")
public class ControllerClientes
{
    // Inyecta el repositorio de clientes
    @Autowired
    private ClientesRepository clientesRepository;

    //devuelve todos los clientes
    @GetMapping
    public List<Clientes> getAll()
    {
        return clientesRepository.findAll();
    }

    //devuelve un cliente por su ID
    @GetMapping("/{id}")
    public Clientes getById(@PathVariable Long id)
    {
        return clientesRepository.findById(id).orElse(null);
    }

    //crea un nuevo cliente
    @PostMapping
    public Clientes create(@RequestBody Clientes clientes)
    {
        return clientesRepository.save(clientes);
    }

    //actualiza un cliente existente
    @PutMapping("/{id}")
    public Clientes update(@PathVariable Long id, @RequestBody Clientes clientes)
    {
        if (clientesRepository.existsById(id)) {
            clientes.setIdCliente(id);
            return clientesRepository.save(clientes);
        }
        return null; // o lanzar una excepción si el cliente no existe
    }

    //elimina un cliente por su ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        clientesRepository.deleteById(id);
    }
    
}
