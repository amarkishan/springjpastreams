package com.springjpastreams.demo.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springjpastreams.demo.entity.Employee;
import com.springjpastreams.demo.repo.Employeerepo;

@Service
public class Employeeservice 
{
	
	@Autowired
	//save employee
	private Employeerepo employeerepo; 
	public Employee  saveEmployee(Employee employee)
	{
	  return employeerepo.save(employee);

	 }

	 //list of all employees
     public List<Employee> getAllemployees()
     {
	  List<Employee> empList=new ArrayList<Employee>();
	  employeerepo.findAll().forEach(empList::add);
	  return empList; 
	   
     }

    //Filter employees by department: Get a list of all employees who belong to a specific department.
	public List<Employee> depemp()
	{



		 List<Employee> emp  = (List<Employee>) employeerepo.findAll();
          return emp.stream()
				  .filter(e->e.getDepartment().equals("Testing"))
				  .collect(Collectors.toList());

	}

	//Create a list of all employee names.
	 public List<String> employeenames()
	 {
		 List<String> names=new ArrayList<>();
		 employeerepo.findAll().forEach(e->names.add(e.getName()));
		 return names;
	 }

	//Count employees: Find out the total number of employees in the database.
	 public int Countemp()
	 {

		 return (int) employeerepo.count();

	 }
     //highest and lowest salary
	 public OptionalDouble[] highestandlowest()
	 {
		 List<Employee> emp  = (List<Employee>) employeerepo.findAll();

		 OptionalDouble maxsalary = emp.stream()
				 .mapToDouble(Employee::getSalary)
				 .max();

		 OptionalDouble minsalary = emp.stream()
				 .mapToDouble(Employee::getSalary)
				 .min();


		 return new OptionalDouble[]{maxsalary,minsalary};
	 }


	 //highest salary
	 public double highest()
	 {
		 List<Employee> emp  = (List<Employee>) employeerepo.findAll();
		 return emp.stream()
				 .mapToDouble(Employee::getSalary)
				 .max()
				 .orElse(0);

	 }

     //lowest salary
	 public double lowest()
	 {

		 List<Employee> emp  = (List<Employee>) employeerepo.findAll();

		 return emp.stream()
				 .mapToDouble(Employee::getSalary)
				 .min()
				 .orElse(0);
	 }

	 //highest and lowest salary using map
	 public Map<String, Double> highestandlowestmap()
	 {

		 List<Employee> emp  =  employeerepo.findAll();

		 Map<String,Double> hm = new HashMap<String,Double>();

       for(Employee e:emp)
	   {
		   hm.put(e.getName(),e.getSalary());
	   }

	   Map<String,Double> m = new HashMap<String,Double>();
	   Map.Entry<String,Double> maxEntry=

		       hm.entrySet()
				 .stream()
				 .max(Map.Entry.comparingByValue())
				 .orElse(null);
	   Map.Entry<String,Double> minEntry=
			   hm.entrySet()
					   .stream()
					   .min(Map.Entry.comparingByValue())
					   .orElse(null);
	   Map<String,Double> result= new HashMap<>();
	   result.put(maxEntry.getKey(), maxEntry.getValue());
	   result.put(minEntry.getKey(),minEntry.getValue());

	   return result;

	 }

	//Group employees by department: Group all employees by their department and return a map.

    public Map<String, List<Employee>> groupbydep()
	{

		List<Employee> employees = employeerepo.findAll();

		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));


	}
	//Sort employees by salary: Return a list of employees sorted by their salary in ascending or descending order.
public List<Employee>  salary()
{
	List<Employee> employees = employeerepo.findAll();
     return employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary))
            .collect(Collectors.toList());

}

	//Calculate average salary by department: Compute the average salary of employees
	// for each department.

	public Map<String,Double> avgsalarybydep()
	{
		List<Employee> employees = employeerepo.findAll();


        //employees.stream()
        //				.map(Employee::getSalary)
        //				.reduce(0.0,(sum,salary) -> sum + salary);
		return employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						Collectors.averagingDouble(Employee::getSalary)));

	}

	//Find employees with salaries above average: Identify all employees who have a salary
	// above the average salary.

	public List<Employee> avgsalarygreater()
	{

		List<Employee> employees = employeerepo.findAll();
		Double asal =  employees.stream()
				.mapToDouble(Employee::getSalary)
				.average()
				.orElse(0.0);

		return employees.stream().filter(e ->e.getSalary()>asal).collect(Collectors.toList());



	}

    //Create a summary report: Generate a summary report that includes total employees,
	// average salary, highest salary, and lowest salary for each department.

	public void summaryreport()
	{
		List<Employee> employees = employeerepo.findAll();
		Map <String,List<Employee>> m = employees.stream()
			.collect(Collectors.groupingBy(Employee::getDepartment));
		m.forEach((department,emplist)->
		{
			System.out.println("Department"+department);
			System.out.println("Total employees"+emplist.size());
		});
		double averageSalary = employees.stream()
				.mapToDouble(Employee::getSalary)
				.average()
				.orElse(0.0);
		System.out.println("Average Salary: " + averageSalary);

		double highestSalary = employees.stream()
				.mapToDouble(Employee::getSalary)
				.max()
				.orElse(0.0);
		System.out.println("Highest Salary: " + highestSalary);

		double lowestSalary = employees.stream()
				.mapToDouble(Employee::getSalary)
				.min()
				.orElse(0.0);
		System.out.println("Lowest Salary: " + lowestSalary);


	}
}
