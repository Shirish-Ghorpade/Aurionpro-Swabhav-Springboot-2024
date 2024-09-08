package com.aurionpro.connect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aurionpro.connect.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class CustomerRespositoryImpl implements CustomerRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub

		TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
		return query.getResultList();

	}

	@Override
	public Customer getCustomerById(int customerID) {
		// TODO Auto-generated method stub
		return entityManager.find(Customer.class, customerID);
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if (customer.getCustomerID() == 0) {
			entityManager.persist(customer);
			return;
		}
		entityManager.merge(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if (customer.getCustomerID() == 0) {
			entityManager.persist(customer);
			return;
		}
		entityManager.merge(customer);

	}

	@Override
	public void deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class, customerID);
		if (customer != null) {
			entityManager.remove(customer);
		}
	}
}
