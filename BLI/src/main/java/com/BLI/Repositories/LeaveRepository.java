package com.BLI.Repositories;

import com.BLI.Domain.Leave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends CrudRepository<Leave,Long> {
  //  List<Leave> findByEmployeeName(String name);
}
