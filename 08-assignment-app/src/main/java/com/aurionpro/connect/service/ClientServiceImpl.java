package com.aurionpro.connect.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aurionpro.connect.entity.Client;
import com.aurionpro.connect.respository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Page<Client> getAllClients(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return clientRepository.findAll(pageable);
	}

	@Override
	public Page<Client> getAllClientsByCompanyName(String name, int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return clientRepository.findAllByCompanyName(name, pageable);
	}

	@Override
	public Optional<Client> getClientById(int clientId) {
		return clientRepository.findById(clientId);
	}

	@Override
	public String addClient(Client client) {
		clientRepository.save(client);
		return "We add client sucessfully";
	}


	@Override
	public String deleteClient(int clientId) {
		clientRepository.deleteById(clientId);
		return "Delete client sucessfully";
	}
}
