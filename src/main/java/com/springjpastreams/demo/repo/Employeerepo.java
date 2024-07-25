package com.springjpastreams.demo.repo;

import com.springjpastreams.demo.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Employeerepo extends CrudRepository<Employee,Integer>
{

	List<Employee> findAll();
   
}





