package com.Job.Management.Repos;

import com.Job.Management.Models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface EmployeeRepo extends MongoRepository<Employee, UUID> {

    @Query("{'employeeId' :?0 }")
    Employee findByEmployeeId(UUID employeeId);

    @Query("{'employeeName' :?0 }")
    Optional<Employee> findByEmpName(String employeeName);

}