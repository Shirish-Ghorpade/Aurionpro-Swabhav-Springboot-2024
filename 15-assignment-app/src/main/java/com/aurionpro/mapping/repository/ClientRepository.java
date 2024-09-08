package com.aurionpro.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mapping.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
}
