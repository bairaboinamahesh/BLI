package com.BLI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.BLI.Domain.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>{

    Employee findEmployeeById(Long id);
    Employee findEmployeeByEmailId(String emailId);

//    Employee findEmployeeByUserName(String username);
}
