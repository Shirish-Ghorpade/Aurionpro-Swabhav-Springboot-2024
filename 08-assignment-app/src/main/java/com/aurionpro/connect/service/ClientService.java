package com.aurionpro.connect.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.aurionpro.connect.entity.Client;

public interface ClientService {
	Page<Client> getAllClients(int pageNo, int pageSize, String sortBy);
	Page<Client> getAllClientsByCompanyName(String name, int pageNo, int pageSize, String sortBy);
	Optional<Client>getClientById(int clientId);
	String addClient(Client client);
	String deleteClient(int clientId);
}
