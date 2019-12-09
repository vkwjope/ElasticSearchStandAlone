package com.example.elasticsearch.repo;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elasticsearch.model.Employee;

@Repository
public interface ElasticRepo extends ElasticsearchRepository<Employee, String> {

	List<Employee> findAll();

}
 