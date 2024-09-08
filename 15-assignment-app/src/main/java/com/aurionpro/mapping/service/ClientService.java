package com.aurionpro.mapping.service;

import java.util.List;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;

public interface ClientService {
	public Client addClient(Client client);

	public Client getClientById(int id);
	
	public String deleteClientById(int id);
	
	public Client updateClientById(int id, Client client);
	
	public Client allocateEmployees(int id, List<Employee>employees);
}
