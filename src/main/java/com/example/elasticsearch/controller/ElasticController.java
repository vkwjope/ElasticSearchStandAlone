package com.example.elasticsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.elasticsearch.impl.ElasticImpl;
import com.example.elasticsearch.model.Employee;

@RestController
@RequestMapping("employee")
public class ElasticController {

	@Autowired
	private ElasticImpl impl;

	@PostMapping
	public Employee addEmployee(@RequestBody Employee emp) {
		return impl.addEmployee(emp);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody Employee emp) {
		impl.updateEmployee(emp);
		return emp;
	}

	@DeleteMapping
	public String deleteEmployee(@RequestParam(value = "empId", required = true) String empId) {
		impl.deleteEmployee(empId);
		return "Emp deleted successfully";
	}

	@GetMapping("/id")
	public Employee getEmployee(@RequestParam(value = "empId", required = true) String empId) {
		Optional<Employee> employee = impl.getEmployee(empId);
		Employee employee2 = null;
		if (employee.isPresent()) {
			employee2 = employee.get();
		}
		return employee2;
	}

	@GetMapping
	public List<Employee> getAllEmployees() {
		return impl.getAllEmployees();
	}

	/**
	 * @return the impl
	 */
	public ElasticImpl getImpl() {
		return impl;
	}

	/**
	 * @param impl the impl to set
	 */
	public void setImpl(ElasticImpl impl) {
		this.impl = impl;
	}

}
