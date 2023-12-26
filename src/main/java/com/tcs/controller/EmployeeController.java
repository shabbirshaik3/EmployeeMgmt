package com.tcs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.model.Employee;
import com.tcs.repository.EmployeeRepository;

import jakarta.validation.Valid;


@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;
	
	@PostMapping("/saveEmployee")
	public boolean saveEmployee(@Valid @RequestBody Employee emp) {
		try {
			empRepository.save(emp);
			return true;
		}catch(Exception exception) {
			return false;
		}
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex){
		Map<String, String> map = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->{
			map.put(error.getField(), error.getDefaultMessage());
		});
		return map;
	}
	
	@GetMapping("/getAllEmployes")
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}
	
	@GetMapping("/getEmployeeById")
	public Employee getEmployeeById(@RequestParam Integer empId) {
		return empRepository.findById(empId).get();
	}
	
	@PutMapping("/updateEmployee")
	public String updateEmployee(@RequestBody Employee emp) {
		Employee employee = empRepository.findById(emp.getEmpId()).get();
		if(emp.getEmpName()!=null) {
			employee.setEmpName(emp.getEmpName());
		}
		if(emp.getEmpAge()!=null) {
			employee.setEmpAge(emp.getEmpAge());
		}
		if(emp.getEmpSal()!=null) {
			employee.setEmpSal(emp.getEmpSal());
		}
		try {
		empRepository.save(employee);
		return "Employee updated Successfully";
		}catch(Exception exception) {
			return "Emloyee update failed";
		}
	}
	@DeleteMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Integer empId) {
		try {
			empRepository.deleteById(empId);
			return "Employee Deleted Successfully";
		}catch(Exception exception) {
			return "Employee Deletion failed";
		}
	}
	
	
	
}
