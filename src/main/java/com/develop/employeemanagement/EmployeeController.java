package com.develop.employeemanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.boot.MappingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/employee")


public class EmployeeController {

	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/get-all-employees")
	public List<EmployeeEntity> getAllEmployee(){
		try {
		List<EmployeeEntity> allEmployeelist = employeeRepository.findAll();
		return allEmployeelist;
		}
		catch (Exception e) {
			System.out.println("error..." + e);	   	
}
		return null;
}
	@GetMapping("/get-employee/{id}")
	public EmployeeEntity getEmployeebyId(@PathVariable(value = "id") Integer employeeId ,HttpServletResponse response)
      
	{
		try {
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
		
		return employeeEntity;	
		}
		catch (Exception e) {
			EmployeeEntity error_message = new EmployeeEntity("No Item Found");
			return error_message;
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND,"List Not Found",e);
		}			
	}
	
	
	@PostMapping("/create-employees")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {
       try {
    	 EmployeeEntity savedEmployee = employeeRepository.save(employee);
    	 
    	return  savedEmployee;	
       }
       catch (Exception e) {
			System.out.println("error..." + e);	 
			 return null;
		}
      
    }
	
	
	@PutMapping("/update-employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Integer employeeId,
         @RequestBody EmployeeEntity employeeDetails) {
		
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();
        
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setName(employeeDetails.getName());
        employee.setLocation(employeeDetails.getLocation());
        final EmployeeEntity updatedEmployee = employeeRepository.save(employee);
       return ResponseEntity.ok(updatedEmployee);
      
	}
	
    @DeleteMapping("/delete-employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
    {
    	try {
     EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
    	
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    	catch (Exception e) {
		System.out.println("error..." + e);	 
		 return null;
	}
    }
}
