package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT CASE WHEN COUNT(e) > 0 THEN TRUE ELSE FALSE END FROM Employee e WHERE e.employeeId = ?1")
	Boolean isEmployeeByExistId(int id);

	public Employee getEmployeeByEmployeeName(Employee employeeName);

	public Employee getEmployeeByEmployeeId(int employeeId);

	@Query("select e from Employee e where employeeId = :employeeId")
	public Employee getEmployeeByIdByJpa(int employeeId);

	@Query(value = "select * from tbl_employee where employee_id = :employeeId", nativeQuery = true)
	public Employee getEmployeeByIdByNonJpa(int employeeId);

	@Query("select e from Employee e where employeeAddress = :employeeAddress")
	public List<Employee> getEmployeeListByCity(String employeeAddress);

	@Transactional
	@Modifying
	@Query("delete from Employee where employeeId = :employeeId")
	public void deleteEmployee(int employeeId);

	@Query("SELECT e FROM Employee e WHERE employeeName = :employeeName")
	public List<Employee> getEmployeeListByName(String employeeName);
	
	  

}
