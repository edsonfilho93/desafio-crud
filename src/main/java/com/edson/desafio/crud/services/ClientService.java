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
		return new ClientDTO(
				repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		repository.save(entity);
		return new ClientDTO(entity);
	}

//	@Transactional
//	public ClientDTO update(ClientDTO dto) {
//		Client entity = reposi
//		copyDtoToEntity(dto, entity);
//		repository.save(entity);
//		return new ClientDTO(entity);
//	}
//	

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setName(dto.getName());

	}

}
