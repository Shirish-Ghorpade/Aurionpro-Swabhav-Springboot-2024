package com.aurionpro.connect.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.connect.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	Page<Client> findAllByCompanyName(String companyName, Pageable pageable);
}
