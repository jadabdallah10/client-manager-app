package com.clientapp.clientapp.service;

import com.clientapp.clientapp.model.Client;
import com.clientapp.clientapp.repository.ClientRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
public class ClientService {

    private final ClientRepository repo;

    // Constructor injection (Spring automatically gives you the repo)
    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public Client save(Client client) {
        return repo.save(client);
    }

    public Optional<Client> findById(Long id) {
        return repo.findById(id);
    }


    public void deleteById(Long id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // id not found -> ignore or rethrow if you want a 404
        }
    }

    public List<Client> getAll() {
        return repo.findAll();
    }

}

