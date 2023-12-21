package com.BLI.Services;

import com.BLI.Domain.Employee;
import com.BLI.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository ) {
        this.employeeRepository = employeeRepository;
    }
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployee(){
        return (List<Employee>) employeeRepository.findAll();
    }
    public Employee updateEmployee(Long id, Employee employee){
        Employee modifiedEmployee = employeeRepository.findEmployeeById(id);
        modifiedEmployee.setFirstName(employee.getFirstName());
        modifiedEmployee.setEmailId(employee.getEmailId());
        modifiedEmployee.setLastName(employee.getLastName());
        modifiedEmployee.setPhoneNumber(employee.getPhoneNumber());
        modifiedEmployee.setJobTitle(employee.getJobTitle());
        return employeeRepository.save(modifiedEmployee);
    }
    public Employee getEmployee(Long id){
        return employeeRepository.findEmployeeById(id);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Long getEmployeeCount() {
            long count = employeeRepository.count();
            return count;
        }
     public Employee getEmployeeByEmailId( String emailId){
        return employeeRepository.findEmployeeByEmailId(emailId);

     }

}

