package com.aurionpro.mapping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.repository.ClientRepository;
import com.aurionpro.mapping.repository.EmployeeRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Client addClient(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client getClientById(int id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (!optionalClient.isPresent()) {
			return null;
		}
		return optionalClient.get();
	}

	@Override
	public String deleteClientById(int id) {
		clientRepository.deleteById(id);
		return "Client Deleted Sucessfully";

	}

	@Override
	public Client updateClientById(int id, Client client) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (!optionalClient.isPresent()) {
			return null;
		}
		return clientRepository.save(client);
	}

	public Client allocateEmployees(int id, List<Employee> employees) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		if (!optionalClient.isPresent()) {
			return null;
		}
//		Get client
		Client client = optionalClient.get();
		
//		List of employees
		List<Employee> dbEmployees = client.getEmployees();

		employees.forEach((employee) -> {
			Employee emp = employeeRepository.findById(employee.getEmployeeId()).get();
			emp.setClient(client);
			dbEmployees.add(emp);
		});

		client.setEmployees(dbEmployees);
		return clientRepository.save(client);

	}
}
