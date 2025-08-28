package com.clientapp.clientapp.controller;

import com.clientapp.clientapp.model.Client;
import com.clientapp.clientapp.service.ClientService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;


@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public Client saveClient(@RequestBody Client client) {
        return service.save(client);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAll(); // and add a method in service that calls repo.findAll()
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Optional<Client> existing = service.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = existing.get();
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setAge(updatedClient.getAge());

        return ResponseEntity.ok(service.save(client));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    // optional simple health check
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
