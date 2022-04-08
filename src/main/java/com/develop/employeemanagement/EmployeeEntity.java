package com.develop.employeemanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.bytebuddy.asm.Advice.Return;

@Entity
@Table(name = "employes")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "email_address", nullable = false)
	private String emailId;

	private String error;

	public EmployeeEntity() {

	}

	public EmployeeEntity(String error) {
		this.error = error;
	}

	public EmployeeEntity(String name, String location, String emailId) {
		this.name = name;
		this.location = location;
		this.emailId = emailId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getErrorMessage() {
		return error;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", emailId=" + emailId + "]";
	}

}
