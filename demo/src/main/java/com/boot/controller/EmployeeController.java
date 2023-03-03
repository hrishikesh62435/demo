package com.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Employee;
import com.boot.repository.EmployeeRepository;

@RestController
@CrossOrigin
public class EmployeeController {
	//hello
	EmployeeController() {
		System.out.println("employee REST controller called");
	}

	@Autowired
	EmployeeRepository empRepo;

	Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/saveEmployee")
	public @ResponseBody Employee saveEmployee(@RequestBody Employee emp) {

		Employee emp1 = empRepo.save(emp);

		return emp1;
	}

	@GetMapping("/getEmployeeById/{employeeId}")
	public @ResponseBody Employee getEmployeeById(@PathVariable int employeeId) {

		Employee emp1 = null;
		try {
			emp1 = empRepo.getEmployeeByIdByJpa(employeeId);
			log.info("getEmployeeById fetched : " + employeeId);
		} catch (Exception e) {
			log.error("Error at getEmployeeById : " + e.toString());
		}

		return emp1;
	}

	@GetMapping("/getEmployeeByName/{employeeName}")
	public @ResponseBody Employee getEmployeeByName(@PathVariable Employee employeeName) {

		Employee emp1 = empRepo.getEmployeeByEmployeeName(employeeName);

		return emp1;
	}

	@GetMapping("/allEmployees")
	public @ResponseBody List<Employee> allEmployees() {

		List<Employee> empList = empRepo.findAll();

		return empList;
	}

	@PostMapping("/getEmployeeListByCity")
	public @ResponseBody List<Employee> getEmployeeListByCity(@RequestBody Employee e) {

		System.out.println(e);

		List<Employee> empList = empRepo.getEmployeeListByCity(e.getEmployeeAddress());

		return empList;
	}

	@PostMapping("/deleteEmployee")
	public void deleteEmployee(@RequestBody Employee e) {

		// empRepo.deleteById(e.getEmployeeId());
		empRepo.deleteEmployee(e.getEmployeeId());
		// return new ResponseEntity<>(HttpStatus.OK);
	}

}
