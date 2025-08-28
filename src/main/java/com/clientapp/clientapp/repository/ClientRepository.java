package com.clientapp.clientapp.repository;

import com.clientapp.clientapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository gives you .save(), .findAll(), .deleteById(), etc.
public interface ClientRepository extends JpaRepository<Client, Long> {
}