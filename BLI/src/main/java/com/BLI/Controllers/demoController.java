package com.BLI.Controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
class demoController {

    @PostMapping("/{id}")
    public ResponseEntity getDemo(@PathVariable("id") Long  id){
        System.out.println(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
