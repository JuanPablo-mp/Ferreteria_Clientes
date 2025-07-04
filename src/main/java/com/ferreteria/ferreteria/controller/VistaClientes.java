package com.ferreteria.ferreteria.controller;

import com.ferreteria.ferreteria.model.Clientes;
import com.ferreteria.ferreteria.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VistaClientes {

    @Autowired
    private ClientesRepository clientesRepository;

    // Mostrar lista de clientes
    @GetMapping("/vista/clientes")
    public String getAll(Model model) {
        model.addAttribute("clientes", clientesRepository.findAll());
        return "clientes";
    }

    // Mostrar formulario para registrar nuevo cliente
    @GetMapping("/vistaC/form")
    public String form(Model model) {
        model.addAttribute("clientes", new Clientes());
        return "formClientes";
    }

    // Guardar cliente (nuevo o editado)
    @PostMapping("/vistaC/save")
    public String save(@ModelAttribute("clientes") Clientes clientes, RedirectAttributes ra) {
        System.out.println("Guardando cliente: " + clientes.getIdCliente() + " - " + clientes.getNombre());
        clientesRepository.save(clientes);
        ra.addFlashAttribute("success", "Cliente guardado exitosamente");
        return "redirect:/vista/clientes";
    }

    // Cargar cliente para edición
    @GetMapping("/vistaC/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Clientes cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("clientes", cliente);
        return "formClientes";
    }

    // Eliminar cliente
    @PostMapping("/vistaC/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        clientesRepository.deleteById(id);
        ra.addFlashAttribute("success", "Cliente eliminado exitosamente");
        return "redirect:/vista/clientes";
    }
}