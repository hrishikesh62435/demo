package com.boot.testRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.model.Employee;
import com.boot.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeTestRepository {
	
	
	@Autowired
	private EmployeeRepository EmpRepo;
	
	@Test
	void isEmployeeByExistId() {
		
		Employee emp=new Employee(100,"rushikesh","nashik",80000);
		EmpRepo.save(emp);
		System.out.println(emp);
		Boolean actual = EmpRepo.isEmployeeByExistId(100);
		assertThat(actual).isTrue();		
			}
	

}
