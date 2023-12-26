package com.tcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
public class Employee {
	@Id
	private Integer empId;
	@NotNull(message = "emp name should not be null" )
	private String empName;
	@Column(name = "EMP_SALARY")
	private Integer empSal;
	@Min(value = 21, message = "emp age min should be 21")
	@Max(value=62,  message = "emp age max limited to 62")
	private Integer empAge;
	private String empEmail;
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getEmpSal() {
		return empSal;
	}
	public void setEmpSal(Integer empSal) {
		this.empSal = empSal;
	}
	public Integer getEmpAge() {
		return empAge;
	}
	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}
	
	

}
