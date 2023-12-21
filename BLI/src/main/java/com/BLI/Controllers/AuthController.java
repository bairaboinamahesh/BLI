package com.BLI.Controllers;

import com.BLI.Domain.Employee;
import com.BLI.Domain.JwtResponse;
import com.BLI.Services.EmployeeService;
import com.BLI.configuration.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.BLI.Domain.JwtRequest;

import java.util.UUID;


@RestController
@RequestMapping("/auth")
//@CrossOrigin
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        if(response != null){
            System.out.println("working...");
            Employee employee = employeeService.getEmployeeByEmailId(request.getEmail());
            employee.setOnline(true);
            employeeService.updateEmployee(employee.getId(), employee);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
      //  String pass = UUID.randomUUID().toString();
        String pass = employee.getPhoneNumber();

        System.out.println(pass + " " + employee.getEmailId());
        employee.setPassword(passwordEncoder.encode(pass));

        employee.setUserName(employee.getFirstName()+employee.getLastName()+"@BLI");
        Employee newEmployee = employeeService.addEmployee(employee);

        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable("id") Long id){
        System.out.println(id + "from /logout");
        Employee employee = employeeService.getEmployee(id);
        employee.setOnline(false);
        employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
