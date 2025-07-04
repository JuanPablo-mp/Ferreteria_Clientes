package com.ferreteria.ferreteria.repository;

import com.ferreteria.ferreteria.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long>
{

}
