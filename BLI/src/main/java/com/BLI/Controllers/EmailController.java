package com.BLI.Controllers;

import com.BLI.Domain.Email;
import com.BLI.Domain.Employee;
import com.BLI.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    private Email email = new Email();
    private Employee employee = new Employee();

    @PostMapping("/{emailId}")
    public ResponseEntity<?> sendMail(@PathVariable("emailId") String emailId){
        System.out.println(emailId + "calling /email");
        email.setSendTo(emailId);
        emailService.sendMail(email);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }
}
