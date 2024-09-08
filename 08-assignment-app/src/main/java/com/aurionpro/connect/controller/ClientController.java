package com.aurionpro.connect.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.connect.entity.Client;
import com.aurionpro.connect.service.ClientService;


@RestController
@RequestMapping("/clientapp")
public class ClientController {
	@Autowired
	private ClientService clientService;


	@GetMapping("/client")
	public ResponseEntity<Page<Client>> getAllClientByCompanyName(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0", required = false) int pageNo,
			@RequestParam(defaultValue = "3", required = false) int pageSize, 
			@RequestParam (defaultValue = "clientId", required = false) String sortBy) {
		if (name != null)
			return ResponseEntity.ok(clientService.getAllClientsByCompanyName(name, pageNo, pageSize, sortBy));
		return ResponseEntity.ok(clientService.getAllClients(pageNo, pageSize, sortBy));
	}

	@GetMapping("/client/{id}")
	public ResponseEntity<Optional<Client>> getClientById(@PathVariable int id) {
		return ResponseEntity.ok(clientService.getClientById(id));
	}
	
	@PostMapping("/client")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		return ResponseEntity.ok(clientService.addClient(client));
	}
	
	@DeleteMapping("/client/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable int id) {
		return ResponseEntity.ok(clientService.deleteClient(id));
	}
}
