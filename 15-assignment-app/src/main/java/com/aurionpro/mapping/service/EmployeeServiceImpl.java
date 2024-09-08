package com.aurionpro.mapping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mapping.entity.Client;
import com.aurionpro.mapping.entity.Employee;
import com.aurionpro.mapping.repository.ClientRepository;
import com.aurionpro.mapping.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Optional<Employee> optionEmployee = employeeRepository.findById(id);
		if (!optionEmployee.isPresent())
			return null;
		return optionEmployee.get();
	}

	@Override
	public Employee updateEmployee(int id, Employee employeeUpdatedData) {
		Optional<Employee> optionEmployee = employeeRepository.findById(id);
		if (!optionEmployee.isPresent())
			return null;
//		Employee employeeOldData = optionEmployee.get();
//		employeeOldData.setEmployeeId(employeeUpdatedData.getEmployeeId());
//		employeeOldData.setFirstName(employeeUpdatedData.getFirstName());
//		employeeOldData.setLastName(employeeUpdatedData.getLastName());
//		employeeOldData.setPhoneNumber(employeeUpdatedData.getPhoneNumber());
//		employeeOldData.setEmail(employeeUpdatedData.getEmail());
//		employeeOldData.setPosition(employeeUpdatedData.getPosition());
//		employeeOldData.setHiredate(employeeUpdatedData.getHiredate());
//		employeeOldData.setSalary(employeeUpdatedData.getSalary());
//		employeeOldData.setBankAccountNumber(employeeUpdatedData.getBankAccountNumber());
//		employeeOldData.setStatus(employeeUpdatedData.getStatus());
		return employeeRepository.save(employeeUpdatedData);
	}

	@Override
	public String deleteEmployeeById(int id) {
		Optional<Employee> optionEmployee = employeeRepository.findById(id);
		if (!optionEmployee.isPresent())
			return "Employee Not Found";

		employeeRepository.deleteById(id);
		return "Employee deleted sucessfully";
	}

	@Override
	public Employee allocateClient(int employeeId, Client client) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if (!optionalEmployee.isPresent()) {
			return null;
		}
		Employee dbEmployee = optionalEmployee.get();

		Optional<Client> optionalClient = clientRepository.findById(client.getClientId());
		if (!optionalClient.isPresent()) {
			return null;
		}
		Client dbClient = optionalClient.get();

		dbEmployee.setClient(dbClient);
		return employeeRepository.save(dbEmployee);

	}

//	@Override
//	public SalaryAccount getSalaryByEmployeeId(int EmployeeId) {
//		Optional<Employee> employeeOption = employeeRepository.findById(EmployeeId);
//		if (!employeeOption.isPresent())
//			return null;
//		Employee dbEmployee = employeeOption.get();
//		return dbEmployee.getCla
//	}
//
//	@Override
//	public Address updateAddressByEmployeeRollNumber(int rollNumber, Address address) {
//		Optional<Employee> employeeOption = employeeRepository.findById(rollNumber);
//		if (!employeeOption.isPresent())
//			return null;
//		Employee dbEmployee = employeeOption.get();
//		Address dbAddress = dbEmployee.getAddress();
//
//		dbAddress.setCityName(address.getCityName());
//
//		dbEmployee.setAddress(dbAddress);
//		employeeRepository.save(dbEmployee);
//		return dbEmployee.getAddress();
//
//	}

}
