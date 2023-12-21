package com.BLI.Services;

import com.BLI.Domain.Leave;
import com.BLI.Repositories.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

//    public List<Leave> findLeavebyEmployeeName(String name) {
//       return leaveRepository.findByEmployeeName(name);
//    }
}
