package com.edson.desafio.crud.services;

import com.edson.desafio.crud.dto.ClientDTO;
import com.edson.desafio.crud.entities.Client;
import com.edson.desafio.crud.repositories.ClientRepository;
import com.edson.desafio.crud.services.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return new ClientDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
    }

}
