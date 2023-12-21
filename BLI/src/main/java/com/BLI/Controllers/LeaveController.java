package com.BLI.Controllers;

import com.BLI.Domain.Leave;
import com.BLI.Services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Employee/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

//    @PostMapping("/username")
//    public ResponseEntity<List<Leave>> getLeaveDetialsofUser(@RequestBody String name){
//        List<Leave> employeeleave = leaveService.findLeavebyEmployeeName(name);
//        return new ResponseEntity<>(employeeleave, HttpStatus.OK);
//    }


}
