package com.aurionpro.model.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.model.entity.Customer;
import com.aurionpro.model.entity.Document;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Page<Document> findByCustomer(Customer customer, Pageable pageable);
	
	List<Document> findByCustomer(Customer customer);
}
