package com.example.elasticsearch.impl;

import java.util.List;
import java.util.Optional;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.elasticsearch.exception.NoRecordsFoundException;
import com.example.elasticsearch.model.Employee;
import com.example.elasticsearch.repo.ElasticRepo;

@Service
public class ElasticImpl {
	@Autowired
	private ElasticRepo repo;

	@Autowired
	ElasticsearchOperations elasticsearchTemplate;

	/**
	 * @return the restaurantRepository
	 */
	public ElasticRepo getRestaurantRepository() {
		return repo;
	}

	/**
	 * @param restaurantRepository the restaurantRepository to set
	 */
	public void setRestaurantRepository(ElasticRepo restaurantRepository) {
		this.repo = restaurantRepository;
	}

	public Employee addEmployee(Employee emp) {
		repo.save(emp);
		elasticsearchTemplate.refresh("employee");
		return emp;
	}

	public void updateEmployee(Employee emp1) {
		repo.save(emp1);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = (List<Employee>) repo.findAll();
		return list;
	}

	public void deleteEmployee(String id) {
		List<Employee> list = (List<Employee>) repo.findAll();
		boolean isValid = false;
		for (Employee employee : list) {
			if (employee.getEmpId().equalsIgnoreCase(id)) {
				repo.deleteById(id);
				isValid = true;
				break;
			}
		}
		elasticsearchTemplate.refresh("employee");
		if (!isValid) {
			throw new NoRecordsFoundException(String.valueOf(HttpStatus.NO_CONTENT), "111",
					"No records found to delete");
		}

	}

	public Optional<Employee> getEmployee(String id) {
		Optional<Employee> employee = repo.findById(id);
		return employee;

	}

	public void getEmployees() {
		// TODO Auto-generated method stub

	}

}
