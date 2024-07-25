package com.springjpastreams.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springjpastreams.demo.entity.Employee;
import com.springjpastreams.demo.service.Employeeservice;

@RestController
public class Employeecontroller 
{
	
	@Autowired
	private Employeeservice empservice;
	@PostMapping("/addemp")
	public void  addBook(@RequestBody Employee emp)
	{
		empservice.saveEmployee(emp);
	}	
	
	@GetMapping("/getall")
	public List<Employee>  getemployeeinfo()
	{
		
		return  empservice.getAllemployees();
		
	}

	@GetMapping("/depemp")
	public List<Employee> depemp()
	{
		return empservice.depemp();

	}

	@GetMapping("/employeenames")
	public List<String> employname()
 	{
		return empservice.employeenames();
	}

	@GetMapping("/highestandlowest")
	public OptionalDouble[] employeeinfo()
	{
		return empservice.highestandlowest();
	}

	@GetMapping("/highest")
	public double highest()
	{
		 return empservice.highest();
	}

	@GetMapping("/lowest")
	public  double lowest()
	{
		return empservice.lowest();
	}

	@GetMapping("/count")
	public int count()
	{
		return empservice.Countemp();
	}
	@GetMapping("/highestandlowestmap")
	public Map<String, Double> highestandlowestmap()
	{
		return empservice.highestandlowestmap();
	}

    @GetMapping("/getdep")
	public Map<String, List<Employee>> groupbydep()
	{
		return empservice.groupbydep();
	}

	@GetMapping("/sal")
	public List<Employee> sal()
	{
		return empservice.salary();
	}
	@GetMapping("/avgsalarybydep")
	public Map<String,Double> avgslary()
	{
		return empservice.avgsalarybydep();
	}
	@GetMapping("/avgsalarygreater")
	public List<Employee>  avgsalarygreater()
	{

		return empservice.avgsalarygreater();
	}

	@GetMapping("/summaryreport")
	public void summaryreport()
	{
		 empservice.summaryreport();
	}




}
