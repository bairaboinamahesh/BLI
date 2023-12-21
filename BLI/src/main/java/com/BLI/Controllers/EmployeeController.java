package com.BLI.Controllers;

import com.BLI.Domain.Employee;
import com.BLI.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("permitAll()")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/Employee")
class EmployeeController {

   @Autowired
   public EmployeeService employeeService;

   @Autowired
   public PasswordEncoder passwordEncoder;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // To get all employees
  @GetMapping("/all")
  public ResponseEntity<List<Employee>> getAllEmployee(){
      List<Employee> employees= employeeService.getAllEmployee();
      return new ResponseEntity<>(employees, HttpStatus.OK);
   }

   // To Add employees
   @PostMapping("/add")
   public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
   //     employee.setPassword(UUID.randomUUID().toString());
       String pass = employee.getPhoneNumber();
       employee.setPassword(passwordEncoder.encode(pass));

       employee.setUserName(employee.getFirstName()+employee.getLastName()+"@BLI");

      Employee newEmployee = employeeService.addEmployee(employee);

      return new ResponseEntity<>(newEmployee, HttpStatus.OK);
   }

   //To Update employee
    @PostMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

   @PutMapping("/update/{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
      Employee modifiedEmployee = employeeService.updateEmployee(id, employee);
      return new ResponseEntity<>(modifiedEmployee, HttpStatus.OK);
   }

   // TO delete Employee
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
      employeeService.deleteEmployee(id);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   // To get single Employee
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
     Employee employee = employeeService.getEmployee(id);
     return new ResponseEntity<>(employee, HttpStatus.OK);
   }

   @GetMapping("/count")
    public Long getEmployeeCount(){
        return employeeService.getEmployeeCount();
   }

   @GetMapping(value = "/currentuser", produces = "application/json")
    public ResponseEntity<String> getLoggedInUser(Principal principal){
        Employee employee = employeeService.getEmployeeByEmailId(principal.getName());
        String employeeName = employee.getFirstName()+" "+employee.getLastName();
        Long employeeId = employee.getId();
     //  String employeeNameInJson = "{\"employeeName\":\"" + employeeName + "\",}";
       String employeeNameInJson = "{"
               + "\"employeeId\":" + employeeId + ","
               + "\"employeeName\":\"" + employeeName + "\""
               + "}";
       System.out.println(employeeNameInJson);
        return  ResponseEntity.ok(employeeNameInJson);
   }


}
