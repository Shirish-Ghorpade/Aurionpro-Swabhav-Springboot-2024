package com.aurionpro.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.service.ClientService;

@RestController
@RequestMapping("/bankapp")
public class ClientController {

	@Autowired
	ClientService clientService;

	@PostMapping("/client")
	public ResponseEntity<Client> addNewClient(@RequestBody Client client) {
		return ResponseEntity.ok(clientService.addClient(client));
	}

	@GetMapping("/client")
	public ResponseEntity<Client> getClientById(@RequestParam int id) {
		return ResponseEntity.ok(clientService.getClientById(id));
	}

	@DeleteMapping("/client")
	public ResponseEntity<String> deleteClientById(@RequestParam int id) {
		return ResponseEntity.ok(clientService.deleteClientById(id));
	}

	@PutMapping("/client")
	public ResponseEntity<Client> updateClientById(@RequestParam int id, @RequestBody Client client) {
		return ResponseEntity.ok(clientService.updateClientById(id, client));
	}

	@PutMapping("/client/employees")
	public ResponseEntity<Client> allcateEmployees(@RequestParam int id, @RequestBody List<Employee>employees) {
		return ResponseEntity.ok(clientService.allocateEmployees(id, employees));
	}

}
